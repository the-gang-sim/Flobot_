import numpy as np 
import os
import PIL
import tensorflow as tf
from tensorflow import keras 
from tensorflow.keras import layers
from tensorflow.python.keras.layers import Dense, Flatten
from tensorflow.keras.models import Sequential 
from tensorflow.keras.optimizers import Adam
from PIL import Image
import cv2
import pathlib
from pathlib import Path

data_dir = Path('C:/Users/hk-10/.keras/datasets/flower_photos')
data_dir = pathlib.Path(data_dir)
img_height,img_width=180,180
batch_size=32
train_ds = tf.keras.preprocessing.image_dataset_from_directory(data_dir, validation_split=0.2, subset="training", seed=123, label_mode='categorical', image_size=(img_height, img_width), batch_size=batch_size)
val_ds = tf.keras.preprocessing.image_dataset_from_directory(data_dir, validation_split=0.2, subset="validation", seed=123, label_mode="categorical", image_size=(img_height, img_width), batch_size=batch_size)
global class_names
class_names = train_ds.class_names
global resnet_model
resnet_model = Sequential()
pretrained_model= tf.keras.applications.ResNet50(include_top=False,
                   input_shape=(180,180,3),
                   pooling='avg',classes=5,
                   weights='imagenet')
for layer in pretrained_model.layers:
        layer.trainable=False

resnet_model.add(pretrained_model)
resnet_model.add(Flatten())
resnet_model.add(Dense(512, activation='relu'))
resnet_model.add(Dense(5, activation='softmax'))
resnet_model.compile(optimizer=Adam(lr=0.001),loss='categorical_crossentropy',metrics=['accuracy'])
epochs=1
history = resnet_model.fit(train_ds, validation_data=val_ds, epochs=epochs)