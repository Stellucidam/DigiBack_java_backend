#!/usr/bin/env python
# coding: utf-8

# In[1]:


from pathlib import Path
import sys
import numpy as np
import pandas as pd

from matplotlib import pyplot as plt

from sklearn import neighbors as knn
from sklearn.decomposition import PCA
from sklearn.metrics import confusion_matrix as cm
from sklearn.preprocessing import StandardScaler

# get_ipython().run_line_magic('matplotlib', 'inline')


# ## Read the data

# In[2]:


# list the recordings in folder
# each recording folder must be named with the first word as the label, followed by an underscore:
# e.g. `punch_xxx` and in this folder, the csv file corresponding. 2
base_dir = 'C:\\Users\\clari\\Documents\\HEIG\\BA6\\TB\\java_backend\\src\\main\\java\\ch\\heigvd\\digiback\\python\\data'

# read the data
recordings = {}

files = Path('.').joinpath(base_dir).glob('**/*.csv')

for f in files:
    label = str(f).split('\\')[-1].split('_')[0]
    this_gesture = pd.read_csv(f).set_index('Time (s)')

    if label in recordings.keys():             # if the label already exists in the dictionary
        recordings[label].append(this_gesture) # append the new gesture to the list
    else:
        recordings[label] = [this_gesture]     # otherwise, create a list with one element

labels = recordings.keys()
print(f'{len(labels)} labels found ({labels}).')


# In[3]:


# set the same length to each sample
min_length = sys.maxsize
for label, values in recordings.items():
    for v in values:
        if len(v) < min_length:
            min_length = len(v)
print(f'The minimal length is {min_length}, setting this size for all samples.')

for label, values in recordings.items():
    recordings[label] = [v.head(min_length) for v in values]


# In[4]:


for label in recordings.keys():             # stack the recordings with the same label
    recordings[label] = np.dstack(recordings[label])
    print(f'label {label}: {recordings[label].shape[2]} repetitions.')

print(f'{recordings}')
# the resulting recordings shape is [n_samples x 6 x n_repetitions]


# ## Compute the features

# In[5]:


features = {}
for label in recordings.keys():
    features[label] = np.mean(recordings[label], axis=0).T

# the resulting features shape is [n_repetitions x n_features]


# ## Compilation of a single dataset

# In[6]:


data_input = []
data_output = []
for i, label in enumerate(features.keys()):
    data_input.append(features[label])
    data_output.append(np.repeat(i, features[label].shape[0]))
data_input = np.vstack(data_input)
data_output = np.hstack(data_output)


# ## Visualization with PCA

# In[7]:


scaler = StandardScaler()
data_input_norm = scaler.fit_transform(data_input)
pca = PCA(n_components=data_input_norm.shape[1])
results_pca = pca.fit_transform(data_input_norm)


# In[28]:


plt.figure(figsize=(10, 5))
plt.bar(x=np.arange(len(pca.explained_variance_ratio_)), height=pca.explained_variance_ratio_)
plt.title('Variance of components')
plt.ylabel('Variance')
plt.xlabel('Component')
plt.grid(axis='y')

print(f'First two components keep {100*(pca.explained_variance_ratio_[0] + pca.explained_variance_ratio_[1])} percent of the total variance')
plt.savefig('C:\\Users\\clari\\Documents\\HEIG\\BA6\\TB\\java_backend\\src\\main\\java\\ch\\heigvd\\digiback\\python\\data\\books_read.png')

# In[ ]:


colors = iter(plt.cm.rainbow(np.linspace(0, 1, len(labels))))

plt.figure(figsize=(10, 5))
for i, l in enumerate(sorted(labels)):
    this_labels = results_pca[data_output==i, :]
    plt.scatter(this_labels[:,0], this_labels[:, 1], c=[next(colors)], label=f'label: {l}')
plt.title('PCA visualization')
plt.legend()
plt.grid()


# ## Classification

# In[ ]:


N_NEIGHBORS = 3

# leave-one-out cross-validation:
# given N observations, use N-1 observations to fit the model
# and classify the observation that was not used for fitting

model_classification = -1 * np.ones(len(data_output))

for v_index in range(len(data_output)):
    data_input_calib = np.delete(data_input, v_index, axis=0)
    data_input_test = data_input[v_index:v_index+1, :]

    data_output_calib = np.delete(data_output, v_index)
    data_output_test = data_output[v_index]

    model = knn.KNeighborsClassifier(N_NEIGHBORS)
    #model = knn.KNeighborsClassifier(N_NEIGHBORS, 'uniform')
    #model = knn.KNeighborsClassifier(N_NEIGHBORS, 'distance')
    model.fit(data_input_calib, data_output_calib)

    model_classification[v_index] = model.predict(data_input_test)


# In[ ]:


# accuracy
acc = 100 * np.sum(model_classification == data_output) / len(data_output)
print(f'accuracy: {acc}%')


# In[ ]:


# confusion matrix
confusion_matrix = cm(data_output, model_classification)
print(f'Confusion matrix: \n{confusion_matrix}\n')

plt.figure(figsize=(10, 10))
ax = plt.subplot(111)
plt.matshow(confusion_matrix, 0)
plt.ylabel('Actual label')
plt.xlabel('Prediction')
ax.xaxis.set_label_position('top')
plt.colorbar()

