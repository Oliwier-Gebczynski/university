from threading import Semaphore, Thread

A: int = 0
B: int = 0
C: int = 3

semA = Semaphore(0)
semB = Semaphore(1)
semC = Semaphore(0)

def threadP1():
    semA.acquire()
    global A
    global B
    global C
    A = 10 
    B = B + 5 
    C = C + A
    semC.release()
    print("Thread P1 is done...")

def threadP2():
    global A
    global B
    global C
    B = B + C 
    semA.release()
    semB.acquire()
    A = A + B
    print("Thread P2 is done...")

def threadP3():

    global A
    global B
    global C
    semC.acquire()
    C = B + 10
    A = 2 * A
    semC.acquire()
    B = B + A
    print("Thread P3 is done...")

def threadP4():

    global A
    global B
    global C
    print("Sum result: ",A," + ",B," + ",C," = ",(A + B + C))
    semC.release()
    print("Thread P4 is done...")