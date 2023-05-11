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

loaded_model = keras.models.load_model("C:/Flobot/ma.h5")

data_dir = Path('C:/Flobot/image')
data_dir = pathlib.Path(data_dir)
flower = list(data_dir.glob('./*'))
PIL.Image.open(str(flower[0]))
import cv2
image=cv2.imread(str(flower[0]))
image_resized= cv2.resize(image, (180,180))
image=np.expand_dims(image_resized,axis=0)
pred=loaded_model.predict(image)
output_class=['daisy', 'dandelion', 'roses', 'sunflowers', 'tulips'][np.argmax(pred)]
print(output_class)