import turtle

def drawFirst(turtle, color, size, minbok):
    if size < minbok:
      return
    
    for i in range(0,3,1):
      draw(turtle, color, size/3, minbok)
      turtle.forward(size)
      turtle.left(120)

def drawSecond(turtle, color, size, minbok):
    if size < minbok:
      return
    
    for i in range(0,6,1):
      turtle.forward(size/5)
      turtle.right(120)
      draw(turtle, color, size/2, minbok)
      turtle.left(120)
      turtle.forward(4*(size/5))
      turtle.left(60)

def draw3(turtle, color, size, minbok):
    if size < minbok:
      return
    
    for i in range(0,3,1):
      turtle.right(60)
      draw(turtle, color, size/2, minbok)
      turtle.left(60)
      turtle.forward(size)
      turtle.left(120)

def draw4(turtle, color, size, minbok):
    if size < minbok:
      return
    
    for i in range(0,4,1):
      turtle.right(90)
      draw(turtle, color, size/2, minbok)
      turtle.left(90)
      turtle.forward(size)
      turtle.left(90)

def draw5(turtle, color, size, minbok):
    if size < minbok:
      return
    
    for i in range(0,4,1):
      turtle.forward(2*(size/3))
      turtle.right(90)
      draw(turtle, color, size/3, minbok)
      turtle.left(90)
      turtle.forward(size/3)
      turtle.left(90)

def draw6(turtle, color, size, minbok):
    if size < minbok:
      return
    
    for i in range(0,3,1):
      turtle.forward(2*(size/3))
      turtle.right(60)
      draw(turtle, color, size/3, minbok)
      turtle.left(60)
      turtle.forward(size/3)
      turtle.left(120)

tommy = turtle.Turtle()
tommy.shape("turtle")
tommy.speed(5)

drawFirst(tommy, "green", 200, 20)
drawSecond(tommy, "green", 200, 20)
draw3(tommy, "green", 200, 20)
draw4(tommy, "green", 200, 20)
draw5(tommy, "green", 200, 20)
draw5(tommy, "green", 200, 20)