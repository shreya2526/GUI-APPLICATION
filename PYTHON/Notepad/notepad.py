from tkinter import *
from tkinter.messagebox import showinfo
from tkinter.filedialog import askopenfilename, asksaveasfilename
import os


def newFile():
    global file
    root.title('Untitled-Notpad')
    file=None
    area.delete(1.0,END)

def openFile():
    global file
    file=askopenfilename(defaultextension=".txt",filetypes=[("All Files","*.*"),("text Documents","*.txt")])
    if file =='':
        file=None
    else:
        root.title(os.path.basename(file)+" -Notepad")
        area.delete(1.0,END)
        f=open(file,'r')
        area.insert(1.0,f.read())

def savefile():
    global file
    if file==None:
        file=asksaveasfilename(initialfile='Untitled.txt',defaultextension='.txt',filetypes=[("All Files","*.*"),("text Documents","*.txt")])

        if file=="":
            file=None
    
        else:

            #save as new file

            f=open(file,'w')
            f.write(area.get(1.0,END)) 
            f.close()
            root.title(os.path.basename(file)+"-Notepad")
            print('File Saved')
    
    else:

        #save the file

            f=open(file,'w')
            f.write(area.get(1.0,END)) 
            f.close()

def quitApp():
    root.destroy()

def cut():
    area.event_generate(("<<Cut>>"))

def copy():
    area.event_generate(("<<Copy>>"))

def paste():
    area.event_generate(("<<Paste>>"))

def Editmenu():
    pass

def about():
    showinfo('Notepad',"Notepad by Shreya Chakraborty")


if __name__=='__main__':
    
    #basic tkinter setup

    root=Tk()
    root.title('Untitled- Notepad')
    #root.wm_iconbitmap()
    root.geometry('644x600')

    #add textArea

    area=Text(root,font='lucida 13')
    file=None
    area.pack(expand=True,fill=BOTH)


    #menubar

    Menubar=Menu(root)

    #file menu starts

    FileMenu=Menu(Menubar, tearoff=0)

    #to open a new file
    FileMenu.add_command(label='New',command=newFile)

    #open alreasy exisiting file
    FileMenu.add_command(label="Open", command=openFile)

    #save the current file

    FileMenu.add_command(label="Save",command=savefile)
    FileMenu.add_separator()
    FileMenu.add_command(label="Exit",command=quitApp)
    Menubar.add_cascade(label='File',menu=FileMenu)

    #file menu ends

    #Edit menu starts

    editmenu=Menu(Menubar,tearoff=0)

    #to give a feature of cut
    editmenu.add_command(label="Cut",command=cut)
    editmenu.add_command(label="Copy",command=copy)
    editmenu.add_command(label="Paste",command=paste)
    Menubar.add_cascade(label='Edit',menu=editmenu)

    #edit menu ends

    #help menu starts

    helpmenu=Menu(Menubar,tearoff=0)
    helpmenu.add_command(label="About Notpad",command=about)
    Menubar.add_cascade(label='Help',menu=helpmenu)

    #help menu ends

    root.config(menu=Menubar)
    
    #scroll bar
    
    Scroll=Scrollbar(area)
    Scroll.pack(side=RIGHT,fill=Y)
    Scroll.config(command=area.yview)
    area.config(yscrollcommand=Scroll.set)

    root.mainloop()