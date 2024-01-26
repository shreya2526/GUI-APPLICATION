from tkinter import *
import math
try:
    def click(value):
        ex = e.get()
        ans = ''
        if value == 'C':
            ex = ex[0:len(ex)-1]
            e.delete(0, END)
            e.insert(0, ex)
            return

        elif value == "AC":
            e.delete(0, END)

        elif value == '√':
            ans = math.sqrt(eval(ex))

        elif value == "π":
            ans = math.pi

        elif value == 'cos𝜃':
            ans = math.cos(math.radians(eval(ex)))

        elif value == 'sin𝜃':
            ans = math.sin(math.radians(eval(ex)))

        elif value == 'tan𝜃':
            ans = math.tan(math.radians(eval(ex)))

        elif value == '2π':
            ans = 2*(math.pi)

        elif value == 'cosh':
            ans = math.cosh(eval(ex))

        elif value == 'sinh':
            ans = math.sinh(eval(ex))

        elif value == 'tanh':
            ans = math.tanh(eval(ex))

        elif value == '∛':
            ans = eval(ex)**(1/3)

        elif value == "x\u02b8":
            e.insert(END, '**')
            return

        elif value == "x\u00B3":
            ans = eval(ex)**3

        elif value == "x\u00B2":
            ans = eval(ex)**2

        elif value == "ln":
            ans = math.log2(eval(ex))

        elif value == "deg":
            ans = math.degrees(eval(ex))

        elif value == "rad":
            ans = math.radians(eval(ex))

        elif value == "e":
            ans = math.e

        elif value == "log":
            ans = math.log10(eval(ex))

        elif value == "x!":
            ans = math.factorial(eval(ex))

        elif value == chr(247):
            e.insert(END, "/")
            return

        elif value == '=':
            ans = eval(ex)

        elif value == '*':
            e.insert(END, "*")
            return

        elif value == "-":
            e.insert(END, "-")
            return

        elif value == "+":
            e.insert(END, "+")
            return

        else:
            e.insert(END, value)
            return

        e.delete(0, END)
        e.insert(0, ans)

except SyntaxError:
    pass

def add(a,b):
    return a+b
def sub(a,b):
    return a-b

def mul(a, b):
    return a * b
def div(a, b):
    return a / b

def mod(a, b):
    return a % b

def lcm(a,b):
    l=math.lcm(a,b)
    return l

def hcf(a,b):
    h=math.gcd(a,b)
    return h

operations={'ADD':add,'ADDITION':add,'SUM':add,'PLUS':add,
            'SUBTRACTION':sub , 'DIFFERENCE':sub , 'MINUS':sub , 'SUBTRACT':sub,
            'PRODUCT': mul, 'MULTIPLICATION': mul,'MULTIPLY': mul,
            'DIVISION': div, 'DIV': div, 'DIVIDE': div,
            'LCM':lcm , 'HCF':hcf,
            'MOD':mod ,'REMAINDER':mod , 'MODULUS':mod }

def findNumbers(t):
    l=[]
    for num in t:
        try:
            l.append(int(num))
        except ValueError:
            pass
    return l

cal_root = Tk()
cal_root.title("Scientific Calculator")
cal_root.config(bg='Light Green')
cal_root.geometry('475x315')  # general size
cal_root.resizable(0,0)

# logo=PhotoImage(file="cal.png")
# logoleb=Label(cal_root,image=logo)
# logoleb.grid(row=0,column=0)
e = Entry(cal_root, font=('Rosterine', 20, 'bold'), bg='#ECECEC',
          fg='Black', bd=10, relief=SUNKEN, width=30)
e.grid(row=1, column=0, columnspan=8)

'''
#for background image
photo=PhotoImage(file="background.png")
t1=Label(text="Welcome", bg="Light Green") #visible text on the upper side of interface
t2=Label(image=photo)
t1.pack()
t2.pack()
'''


button_text_list = ["C", "AC", "√", "+", "π", "sin𝜃", "cos𝜃", "tan𝜃",
                    "1", "2", "3", "-", "2π", "sinh", "cosh", "tanh",
                    "4", "5", "6", "*", "∛", "x\u02b8", "x\u00B3", "x\u00B2",
                    "7", "8", "9", chr(247), "ln", "deg", "rad", "e",
                    "0", ".", "%", "=", "log", "(", ")", "x!"]

row_val = 2
col_val = 0


for i in button_text_list:
    button = Button(cal_root, width=5, height=2, bd=2, relief=RAISED, text=i,
                    bg='#b5e2ff', font=('Poppins', 12), activebackground='#D4D4D4', command=lambda button=i: click(button))
    button.grid(row=row_val, column=col_val)
    col_val += 1
    if (col_val > 7):
        row_val += 1
        col_val = 0

cal_root.mainloop()
