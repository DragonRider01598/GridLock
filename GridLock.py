from tkinter import *
from tkinter import messagebox
from random import choice
from time import sleep
global pl1,pl2,size,dine,bot
dine = True
bot = False

def start():  
   def exitf():
      var.set(-1)
      exit(0)
   def sub():
      global pl1,pl2,size,bot
      pl1 = name1.get()
      pl2 = name2.get()
      bot = bool(pls.get())
      size = siz.get()
      var.set(-1)
      window.withdraw()
      grid()
   def pvp():
      if(name1.get()=="Player"):
         name1.delete(0,END)
         name1.insert(0,"Player 1")
      if(name2.get()=="Computer"):
         name2.delete(0,END)
         name2.insert(0,"Player 2")
   def pvc():
      if(name1.get()=="Player 1"):
         name1.delete(0,END)
         name1.insert(0,"Player")
      if(name2.get()=="Player 2"):
         name2.delete(0,END)
         name2.insert(0,"Computer")
   window = Tk()
   window.title("Hello")
   window.protocol("WM_DELETE_WINDOW",lambda x=0:exit(x))
   window.geometry("400x300")
   
   label = Label(window,text="|Grid Lock|",font=("Comic Sans",30))
   label.place(bordermode="outside",x=100,y=20)
   
   label1 = Label(window,text="Player 1:",font=("Comic Sans",15))
   label1.place(x=30,y=100)
   label2 = Label(window,text="Player 2:",font=("Comic Sans",15))
   
   name1 = Entry(window,font = ("Arial",15))
   name1.insert(0,"Player 1")
   name2 = Entry(window,font = ("Arial",15))
   name2.insert(0,"Player 2")
   name1.place(x = 130, y=100)
   label2.place(x=30,y=130)
   name2.place(x=130,y=130)
   pls = IntVar(value = 0)
   siz = IntVar(value = 8)
   
   radbut1 = Radiobutton(window,text = "Player vs Player", variable=pls,value=0,command=pvp,font=("Comic Sans",14))
   radbut2 = Radiobutton(window,text = "Player vs Computer", variable=pls,value=1,command=pvc,font=("Comic Sans",14))
   radbut1.place(x=5,y=170)
   radbut2.place(x=190,y=170)
   
   size8 = Radiobutton(window,text = "8x8 Grid", variable=siz,value=8,font=("Comic Sans",14))
   size16 = Radiobutton(window,text = "16x16 Grid", variable=siz,value=16,font=("Comic Sans",14))
   
   size8.place(x=5,y=200)
   size16.place(x=190,y=200)
   
   var = IntVar(value = 0)
   submit = Button(window,text = "Start",
                  bg="white",
                  fg="black",
                  activebackground="white",
                  activeforeground="black",
                  command=sub,
                  font=("Comic Sans",14))
   exitb = Button(window,text = "Exit",
                  bg="white",
                  fg="black",
                  activebackground="white",
                  activeforeground="black",
                  command=exitf,
                  font=("Comic Sans",14))

   submit.place(x=80,y=240)
   exitb.place(x=220,y=240)
   
   submit.wait_variable(var)
   window.destroy()
   
def grid():
   global pl1,pl2,size,dine
   def bcolor(i,j):
      global dine
      if(dine):
         colo="red"
      else:
         colo="green"
      
      button[i][j].config(activebackground=colo,bg=colo,state="disabled")   
      if(i+1!=size):
         button[i+1][j].config(activebackground=colo,bg=colo,state="disabled")
      if(i-1!=-1):
         button[i-1][j].config(activebackground=colo,bg=colo,state="disabled")   
      if(j+1!=size):
         button[i][j+1].config(activebackground=colo,bg=colo,state="disabled")
      if(j-1!=-1):
         button[i][j-1].config(activebackground=colo,bg=colo,state="disabled")
   def check():
      global size
      p1=0
      p2=0
      g=0
      for i in range(size):
         for j in range(size):
            if(button[i][j]["state"]=="disabled"):
               if(button[i][j]["bg"]=="green"):
                  p1+=1
               elif(button[i][j]["bg"]=="red"):
                  p2+=1
               else:
                  g+=1
      if(p1>p2):
         return 1
      elif(p1<p2):
         return 0
      else: 
         return 2
            
   def cond():
      global size,pl1,pl2
      for i in range(size):
         for j in range(size):
            if(button[i][j]["state"]!="disabled"):
               return True
      if check()==2:
         messagebox.showinfo(title="Game Over",message="It's a draw!")
      else :
         messagebox.showinfo(title="Game Over",message=f"{pl1} won the game!" if check() else f"{pl2} won the game!")
      return False
   def compute(i,j):
      global size
      opt = [[1,1],[1,-1],[-1,1],[-1,-1],[2,2],[2,-2],[-2,2],[-2,-2],[1,2],[1,-2],[-1,2],[-1,-2],[2,1],[2,-1],[-2,1],[-2,-1]]
      for k in range(16):
         a,b = choice(opt)
         a = int(a)
         b = int(b)
         if(0<=i+a<size and 0<=j+b<size):
            if(button[i+a][j+b]["state"] != "disabled"):
               bcolor(i+a,j+b)
               return True
      for k in range(size):
         for l in range(size):
            if(button[k][l]["state"] != "disabled"):
               bcolor(k,l)
               return True
   def loc(i,j):
      global dine,bot
      if(bot):
         dine = False
         label1.config(text=f"Your turn {pl2}",fg="red")
         bcolor(i,j)         
         sleep(0.6)
         if not cond():
            exit(0)
         dine = True
         label1.config(text=f"Your turn {pl1}",fg="green")
         compute(i,j)
         if not cond():
            exit(0)
      else:
         if(dine):
            dine = False
            label1.config(text=f"Your turn {pl2}",fg="red")
         else:
            dine=True
            label1.config(text=f"Your turn {pl1}",fg="green")
         bcolor(i,j)
         if not cond():
            exit(0)
         
      
   window1 = Tk()
   window1.title("Hello again")
   window1.protocol("WM_DELETE_WINDOW",lambda m=0:exit(m))
   button = [[i for i in range(size)] for i in range(size)]
   if(size==8):
      window1.geometry("410x450")
      for i in range(8):
         for j in range(8):
            button[i][j] = Button(window1,activebackground="grey",font=("Comic Sans",19),text="    ",bg="grey",padx=0,pady=0,command=lambda a=i,b=j : loc(a,b))
            button[i][j].place(x=50*i +9,y=50*j + 9)
      exitb = Button(window1,text = "Exit",
                  bg="white",
                  fg="black",
                  activebackground="white",
                  activeforeground="black",
                  command=lambda m=0:exit(m),
                  font=("Comic Sans",14))
      exitb.place(x=300,y=409)
      
      label1 = Label(window1,text=f"Your turn {pl1}",font=("Comic Sans",15),fg="green")
      label1.place(x=10,y=410)
      
   else:
      window1.geometry("608x650")
      for i in range(16):
         for j in range(16):
            button[i][j] = Button(window1,activebackground="grey",font=("Comic Sans",14),text="    ",bg="grey",padx=0,pady=0,command= lambda a=i,b=j: loc(a,b))
            button[i][j].place(x=38*i +6,y=38*j +6)
      exitb = Button(window1,text = "Exit",
                  bg="white",
                  fg="black",
                  activebackground="white",
                  activeforeground="black",
                  command=lambda m=0:exit(m),
                  font=("Comic Sans",14))
      exitb.place(x=540,y=613)
      
      label1 = Label(window1,text=f"Your turn {pl1}",font=("Comic Sans",18),fg="green")
      label1.place(x=20,y=613)
      
   window1.mainloop()
   window1.destroy()

if __name__=="__main__":
   start()