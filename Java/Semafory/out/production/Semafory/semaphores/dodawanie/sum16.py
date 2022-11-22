def threadP1():
    semA.acquire()
    global A
    global B
    global C
    A = 10
    semB.release()
    semA.acquire()
    B = B + 5
    C = C + A
    print("Thread P1 is done...")

def threadP2():
    semB.acquire()
    global A
    global B
    global C
    
    B = B + C
    semD.release()
    semB.acquire()

    A = A + B
    print("Thread P2 is done...")

def threadP3():
    semC.acquire()
    global A
    global B
    global C
    C = B + 10
    A = 2 * A
    B = B + A
    print("Thread P3 is done...")

def threadP4():
    semD.acquire()
    global A
    global B
    global C
    
    print("Sum result: ",A," + ",B," + ",C," = ",(A + B + C))
    print("Thread P4 is done...")
    semA.release()
    semB.release()
    semC.release()