from PIL import Image
import os

folder = "card_images"
for filename in os.listdir(folder):
    if filename.lower().endswith(('.jpg', '.jpeg', '.png')):
        path = os.path.join(folder, filename)
        img = Image.open(path)
        rgb_img = img.convert('RGB')
        rgb_img.save(path)
print("All images in card_images converted to RGB.")

