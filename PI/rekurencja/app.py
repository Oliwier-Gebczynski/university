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

def draw7(turtle, size, minbok):
  if minbok > size:
    return
  
  for n in range(0,3,1):
    turtle.forward(size)
    turtle.right(60)
    gowno(turtle, size/2, minbok)
    turtle.left(180)

# -----------------------------------------------
#trojkat i kolejny na poczatku boku 
def draw(t, size, minbok):
  if minbok > size:
    return
  
  for i in range(0,3,1):
    t.right(60)
    draw(t, size/2, minbok)
    t.left(60)
    t.forward(size)
    t.left(120)

#ilosc wykonan
#size 100, minbok 10, co krok size/2

#1*3  ->  3*3 ->  3*9 ->  3*27  ->    koniec
#1        3       9       27          81
#100      50      25      12.5        6.25
#wynik: 1+3+9+27+81 = 121

# --------------------------------------------------
#kwadrat i koljeny na poczatku boku
def draw(t, size, minbok):
  if minbok > size:
    return
  
  for i in range(0,4,1):
    t.right(90)
    draw(t, size/2, minbok)
    t.left(90)
    t.forward(size)
    t.left(90)

#ilosc wykonan
#size 100, minbok 25, co krok size/2

#1*4 -> 4*4 ->  koniec
#1      4       16 
#100    50      25
#wynik 21

#---------------------------------------------------
#trojkat sierpinskiego 
def draw(t, size, minbok):
  if minbok > size:
    return
  
  for i in range(0,3,1):
    t.forward(size/2)
    t.left(120)
    draw(t, size/2, minbok)
    t.right(120)
    t.forward(size/2)
    t.left(120)

#ilosc wykonan
#size 250, minbok 25, co size/2

#
#1      3       9         27          81
#250    125     67,5      33,75       16cos     
#winik: 121

#-----------------------------------------------------

#rys 10 zadanie A1
def draw(t, size, minbok):
  if minbok > size:
    return
  
  for i in range(0,4,1):
    t.forward(size)
    t.right(90)
    draw(t, size/2, minbok)
    t.left(180)

#rys 13 zadanie A4
def draw(t, size, minbok):
  if minbok > size:
    return
  
  for i in range(0,3,1):
    t.forward(size)
    t.right(60)
    draw(t, size/2, minbok)
    t.left(180)

#rys 14 zadanie A5
def draw(t, size, minbok):
  if minbok > size:
    return
  
  for i in range(0,3,1):
    t.forward(size)
    t.right(120)
    draw(t, size/2, minbok)
    t.left(240)

#rys 15 zadanie A6
def draw(t, size, minbok):
  if minbok > size:
    return
  
  for i in range(0,4,1):
    t.forward(size/4)
    t.right(90)
    draw(t, size/2, minbok)
    t.left(90)
    t.forward(3*(size/4))
    t.left(90)

#rys 16 zaadanie A7
def draw(t, size, minbok):
  if minbok > size:
    return
  
  for i in range(0,3,1):
    t.forward(size/4)
    t.right(60)
    draw(t, size/2, minbok)
    t.left(60)
    t.forward(3*(size/4))
    t.left(120)

tommy = turtle.Turtle()
tommy.shape("turtle")
tommy.speed(5)

drawFirst(tommy, "green", 200, 20)
drawSecond(tommy, "green", 200, 20)
draw3(tommy, "green", 200, 20)
draw4(tommy, "green", 200, 20)
draw5(tommy, "green", 200, 20)
draw5(tommy, "green", 200, 20)
draw5(tommy, "green", 200, 20)