import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class Jbutton extends JButton{
   int i,j,col;
   Jbutton(int i, int j){
      this.i=i;
      this.j=j;
      col=0;
   }
}
class GridLock{
   public static void main(String []args){
      new menu();
   }
}
 class menu extends JFrame implements ActionListener{
   JFrame opp;
   JPanel op;
   JRadioButton playb,bot,sixteen,eight;
   JLabel name,size,playl;
   JButton submit;
   ButtonGroup sz,pl;
   menu(){
      opp = new JFrame("GridLock");
      opp.setResizable(false);
      opp.setVisible(true);
      opp.setDefaultCloseOperation(EXIT_ON_CLOSE);
      opp.setSize(540,450);
      opp.setLayout(null);
      
      op = new JPanel();
      op.setSize(540,450);
      op.setLayout(null);
      op.setVisible(true);

      size = new JLabel("Grid size");
      size.setFont(new Font(null,Font.PLAIN,20));
      size.setSize(100,100);
      size.setLocation(50,251 );
      op.add(size);
      op.validate();
      
      submit = new JButton();
      submit.setText("Submit");
      submit.setBackground(Color.BLACK);
      submit.setForeground(Color.WHITE);
      submit.setFont(new Font(null,Font.PLAIN,30));
      submit.setFocusable(false);
      submit.setSize(150,50);
      submit.setLocation(200,350);
      submit.addActionListener(this);
      submit.setVisible(true);
      op.add(submit);
      op.validate();

      name = new JLabel("| Grid Lock |");
      name.setFont(new Font(null, Font.BOLD, 50));
      name.setSize(300,150);
      name.setForeground(Color.BLACK);
      name.setLocation(130,0);
      op.add(name);
      op.validate();

      playl = new JLabel("Player 1");
      playl.setFont(new Font(null,Font.PLAIN,20));
      playl.setSize(100,100);
      playl.setLocation(50,151 );
      op.add(playl);
      op.validate();

      bot = new JRadioButton("vs. Computer");
      bot.setFont(new Font(null,Font.PLAIN,20));
      bot.setSelected(true);
      bot.setSize(150,100);
      bot.setLocation(201,151);
      bot.setFocusable(false);
      op.add(bot);
      op.validate();
      
      playb = new JRadioButton("vs. Player 2");
      playb.setFont(new Font(null,Font.PLAIN,20));
      playb.setSize(200,100);
      playb.setLocation(351,151);
      playb.setFocusable(false);
      op.add(playb);
      op.validate();

      pl = new ButtonGroup();
      pl.add(playb);
      pl.add(bot);

      sixteen = new JRadioButton("16x16");
      sixteen.setFont(new Font(null,Font.PLAIN,20));
      sixteen.setSelected(true);
      sixteen.setSize(100,100);
      sixteen.setLocation(201,251);
      sixteen.setFocusable(false);
      op.add(sixteen);
      op.validate();
      
      eight = new JRadioButton("8x8");
      eight.setFont(new Font(null,Font.PLAIN,20));
      eight.setSize(100,100);
      eight.setLocation(351,251);
      eight.setFocusable(false);
      op.add(eight);
      op.validate();

      sz = new ButtonGroup();
      sz.add(sixteen);
      sz.add(eight);

      opp.add(op);
   }
   public void actionPerformed(ActionEvent e){
      if(e.getSource()==submit){
         boolean b;
         int si;
         if(bot.isSelected()){
            b = true;
         }else{
            b = false;
         }
         if(sixteen.isSelected()){
            si = 16;
         }else{
            si = 8;
         }
         opp.setVisible(false);
         new but(si,b);
      }
   }

 }
class but extends JFrame implements ActionListener{
   int size,co;
   boolean bot;
   JFrame jff;
   JPanel jf,ts;
   Jbutton b[][],reset = new Jbutton(0,0),check = new Jbutton(0,0);
   but(int a,boolean b){
      size = a;
      bot =b;
      int z,w,h,t;
      if(size==16){
         z=1;
         w=910;
         h=1000;
         t=30;
      }else{
         z=2;
         w=458;
         h=530;
         t=18;
      }
      jff = new JFrame("GridLock");
      jf = new JPanel();
      ts = new JPanel();
      jff.setResizable(false);
      jff.setSize( w, h);
      jf.setSize(900/z,900/z);
      jf.setLayout(new GridLayout(size, size));
      create();
      jff.add(jf);
      jff.setLayout(null);
      ts.setBounds(0,900/z,900/z, 100/z);
      ts.setLayout(new FlowLayout());
      ts.setBackground(Color.GREEN);
      check.setBackground(Color.BLACK);
      check.setForeground(Color.WHITE);
      check.setText("Score");
      check.setFont(new Font(null,Font.CENTER_BASELINE,t));
      check.addActionListener(this);
      check.setFocusable(false);
      reset.setBackground(Color.BLACK);
      reset.setForeground(Color.WHITE);
      reset.setText("Reset");
      reset.setFont(new Font(null,Font.CENTER_BASELINE,t));
      reset.addActionListener(this);
      reset.setFocusable(false);
      ts.add(check);
      ts.add(reset);
      jff.add(ts);
      jff.setDefaultCloseOperation(EXIT_ON_CLOSE);
      jff.setVisible(true); 
      co = 1;
   }
   public void reset(){
      if(JOptionPane.showConfirmDialog(null,"Are you sure you want to reset the Board?","Reset Board", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
         for(int i=0;i<size;i++){
            for(int j =0;j<size;j++){
               b[i][j].col=0;
               b[i][j].setBackground(Color.gray);
            }
         }
      }
   }
   public int random(){
      Random r = new Random();
      return r.nextInt(size);
   }
   public int score(int call){
      int red=0,gray =0,green=0;
      String lol,lols;
      for(int i=0;i<size;i++){
         for(int j =0;j<size;j++){
            if(b[i][j].col==1){
               green++;
            }else if(b[i][j].col==2){
               red++;
            }else if(b[i][j].col==0){
               gray++;
            }
         }
      }
      if(gray!=0&&call==1){
         if(red>green){
            lol ="Red: "+red+"\nGreen: "+green+"\nRed is in the Lead!";
            lols ="Red is winning!";
         }else if(red<green){
            lol ="Red: "+red+"\nGreen: "+green+"\nGreen is in the Lead!";
            lols = "Green is winning";
         }else{
            lol ="Red: "+red+"\nGreen: "+green+"\nIt is a draw!";
            lols = "Toe2Toe";
         }
         JOptionPane.showMessageDialog(null, lol, lols, JOptionPane.DEFAULT_OPTION);
      }else if(gray==0){
         if(red>green){
            lol ="Red: "+red+"\nGreen: "+green+"\nRed has won!";
            lols ="Blushing like a Tomato";
         }else if(red<green){
            lol ="Red: "+red+"\nGreen: "+green+"\nGreen has won!";
            lols = "Tangy like a sour lime";
         }else{
            lol ="Red: "+red+"\nGreen: "+green+"\nIt is a draw!";
            lols = "Toe2Toe";
         }
            JOptionPane.showMessageDialog(null, lol, lols, JOptionPane.DEFAULT_OPTION);
      }
      return gray;
   }
   public void create(){
      b = new Jbutton[size][size];
      for(int i=0;i<size;i++){
         for(int j=0;j<size;j++){
            b[i][j] = new Jbutton(i, j);
            b[i][j].setBackground(Color.gray);
            b[i][j].setFocusable(false);
            b[i][j].addActionListener(this);
            jf.add(b[i][j]);
         }
      }
   }
   public void actionPerformed(ActionEvent e){
      Jbutton a = (Jbutton)e.getSource();
      if(e.getSource()==check){
         score(1);
      }else if(e.getSource()==reset){
         reset();
      }else if(a.col==0&&co==1){
         turn(a,1);
         ts.setBackground(Color.RED);
         co = 2;
         score(0);
         if(bot&&score(0)!=0){bot();}
      }else if(a.col==0&&co==2){
         turn(a,2);
         ts.setBackground(Color.GREEN);
         co = 1;
         score(0);
      }
   }
   public void bot(){
      try{
         Thread.sleep(100);
      }catch(Exception e){
         e.printStackTrace();
      }
      boolean wat=true;
      while(wat){
         wat = !turn(random(),random());
         if(score(0)==0){
            wat=false;
         }
      }
      ts.setBackground(Color.GREEN);
      co = 1;
   }
   public boolean turn(int i, int j){
      int lol = size-1,col = 2; 
      if(b[i][j].col==0){
         if(i>0&&j<lol&&j>0&&i<lol){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else if(i==0&&(j!=0&&j!=lol)){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else if(i==lol&&(j!=0&&j!=lol)){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else if(j==lol&&(i!=0&&i!=lol)){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
         }else if(j==0&&(i!=0&&i!=lol)){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else if(i==j&&i==0){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else if(i==j&&i==lol){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
         }else if(i==0&&j==lol){ 
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
         }else if(i==lol&&j==0){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else{
            return false;
         }
      }else{
         return false;
      }
      return true;
   }
   public void turn(Jbutton a, int col){
      int i =a.i;
      int j = a.j;
      int lol = size-1;
      if(col==1){
         if(i>0&&j<lol&&j>0&&i<lol){
            b[i][j].setBackground(Color.GREEN);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.GREEN);
            b[i-1][j].col = col;
            b[i+1][j].setBackground(Color.GREEN);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.GREEN);
            b[i][j-1].col = col;
            b[i][j+1].setBackground(Color.GREEN);
            b[i][j+1].col = col;
         }else if(i==0&&(j!=0&&j!=lol)){
            b[i][j].setBackground(Color.GREEN);
            b[i][j].col = col;
            b[i+1][j].setBackground(Color.GREEN);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.GREEN);
            b[i][j-1].col = col;
            b[i][j+1].setBackground(Color.GREEN);
            b[i][j+1].col = col;
         }else if(i==lol&&(j!=0&&j!=lol)){
            b[i][j].setBackground(Color.GREEN);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.GREEN);
            b[i-1][j].col = col;
            b[i][j-1].setBackground(Color.GREEN);
            b[i][j-1].col = col;
            b[i][j+1].setBackground(Color.GREEN);
            b[i][j+1].col = col;
         }else if(j==lol&&(i!=0&&i!=lol)){
            b[i][j].setBackground(Color.GREEN);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.GREEN);
            b[i-1][j].col = col;
            b[i+1][j].setBackground(Color.GREEN);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.GREEN);
            b[i][j-1].col = col;
         }else if(j==0&&(i!=0&&i!=lol)){
            b[i][j].setBackground(Color.GREEN);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.GREEN);
            b[i-1][j].col = col;
            b[i+1][j].setBackground(Color.GREEN);
            b[i+1][j].col = col;
            b[i][j+1].setBackground(Color.GREEN);
            b[i][j+1].col = col;
         }else if(i==j&&i==0){
            b[i][j].setBackground(Color.GREEN);
            b[i][j].col = col;
            b[i+1][j].setBackground(Color.GREEN);
            b[i+1][j].col = col;
            b[i][j+1].setBackground(Color.GREEN);
            b[i][j+1].col = col;
         }else if(i==j&&i==lol){
            b[i][j].setBackground(Color.GREEN);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.GREEN);
            b[i-1][j].col = col;
            b[i][j-1].setBackground(Color.GREEN);
            b[i][j-1].col = col;
         }else if(i==0&&j==lol){
            b[i][j].setBackground(Color.GREEN);
            b[i][j].col = col;
            b[i+1][j].setBackground(Color.GREEN);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.GREEN);
            b[i][j-1].col = col;
         }else if(i==lol&&j==0){
            b[i][j].setBackground(Color.GREEN);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.GREEN);
            b[i-1][j].col = col;
            b[i][j+1].setBackground(Color.GREEN);
            b[i][j+1].col = col;
         }
      }else if(col==2){   
         if(i>0&&j<lol&&j>0&&i<lol){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else if(i==0&&(j!=0&&j!=lol)){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else if(i==lol&&(j!=0&&j!=lol)){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else if(j==lol&&(i!=0&&i!=lol)){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
         }else if(j==0&&(i!=0&&i!=lol)){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else if(i==j&&i==0){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }else if(i==j&&i==lol){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
         }else if(i==0&&j==lol){ 
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i+1][j].setBackground(Color.RED);
            b[i+1][j].col = col;
            b[i][j-1].setBackground(Color.RED);
            b[i][j-1].col = col;
         }else if(i==lol&&j==0){
            b[i][j].setBackground(Color.RED);
            b[i][j].col = col;
            b[i-1][j].setBackground(Color.RED);
            b[i-1][j].col = col;
            b[i][j+1].setBackground(Color.RED);
            b[i][j+1].col = col;
         }
      }
   }
}
//created by Aaron Mendonca, hope you enjoyed