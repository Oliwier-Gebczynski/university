import pygame as pg
import numpy as np
import torch
import cv2
from NetworkClass import ANN


def draw(model):
    pg.init()
    window_size = 280   # 10x28
    display_height = window_size + 50
    screen = pg.display.set_mode((window_size, display_height))
    pg.display.set_caption('Digit')
    clock = pg.time.Clock()
    screen.fill((0, 0, 0))
    drawing = False
    prediction = None

    font = pg.font.Font(None, 20)

    while True:
        for event in pg.event.get():
            if event.type == pg.QUIT:
                return
            if event.type == pg.MOUSEBUTTONDOWN:
                drawing = True
            if event.type == pg.MOUSEBUTTONUP:
                drawing = False
                prediction = predict_dig(screen,model)
            if event.type == pg.KEYDOWN:
                if event.key == pg.K_c:
                    screen.fill((0, 0, 0))
                    prediction = None
            if event.type == pg.MOUSEMOTION and drawing:
                pg.draw.circle(screen, (255, 255, 255), event.pos, 8)

            # display
            if prediction is not None:
                text = font.render(f"Prediction: {prediction}", True, (0, 255, 0))
                screen.blit(text, (10,window_size+10))

            pg.display.flip()
            clock.tick(60)

# proces drawning
def process_drawning(screen):
    surface = pg.surfarray.array3d(screen)
    gray = np.dot(surface, [0.299, 0.587, 0.114])
    gray = np.transpose(gray,(1,0))
    gray = cv2.resize(gray,(28,28),interpolation=cv2.INTER_AREA)
    gray = gray.astype(np.float32)/255.0
    gray = (gray-0.5)/0.5
    tensor = torch.tensor(gray, dtype=torch.float32).unsqueeze(0).unsqueeze(0)
    return tensor

# prediction
def predict_dig(screen, model):
    image = process_drawning(screen)
    if  image is None:
        return None

    model.eval()
    with torch.no_grad():
        output = model(image)
        _, predicted = torch.max(output, 1)
    return predicted.item()

model = torch.load("model.pth",weights_only=False)
draw(model)
