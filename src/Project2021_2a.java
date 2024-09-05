////////////////////////////////////////////////////////////////////////////
//
//　　学籍番号：　2172049
//　　氏　　名：　小池温大
//
////////////////////////////////////////////////////////////////////////////
//
//  プログラミング基礎演習Ｂ（Java)　2021年度　プロジェクト課題用ファイル
//
//　　概要：
//　　　１．複数の画像ファイルを配列に入れて使用している。
//　　　２．複数の音声ファイルを配列に入れて使用している。
//　　　３．マウスの「瞬間動作と移動動作」に対するリスナーを組み込んである。
//　　　４．マウスの動作に応じて、音を再生する。
//　　　５．ボタンをクリックしたときに、画面を消して、paintメソッドを呼んでいる。
//
////////////////////////////////////////////////////////////////////////////

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import javax.sound.sampled.*;

public class Project2021_2a extends Frame {

    /*
    　ここは、プログラムのどこからでも使いたい 変数などを宣言しておくフィールドです。
    　変数や、オブジェクトの入れものを用意しています。
     */
    // マウスのｘ座標の記録用変数 Mx,　ｙ座標の記録用変数 Myです。
    int Mx = 0, My = 0;
    //
    // gを、標準グラフィクス描画対象として使います。
    Graphics g;
    //
    // g2を、高度なグラフィクス機能（Graphics2D）を使う場合の描画対象として使います。
    Graphics2D g2;
    //
    // Frame部品の一種である自分を、リスナーオブジェクトから呼び出せるようにするために、
    //　入れ物　myFrame　を用意します
    Frame myFrame;
    //
    // 画像ファイルから読み込む複数の画像を入れるためのオブジェクトです
    //（今回は配列として用意）。
    Image img1[],img2[];
    //
    // 音声ファイルを入れるためのオブジェクトです（今回は配列として用意）。
    Clip clip[];
    //
    // ボタン部品の入れ物を用意します。
    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
    //
    // 描画する画像番号を指定するための変数です（ドラッグ動作ごとに０～９を循環させます）。
    int m = 0,n=0;
    int red=0,blue=0,green=0;
    //
    ///////////////////////////////    
    //①　このプログラムの起動//////    
    public static void main(String[] args){ 
        //②　このプログラム本体の生成
        Project2021_2a pj = new Project2021_2a();  // ③へ
    }

    //③　このプログラムの生成内容 (この部分はコンストラクタと呼ぶ)
    public Project2021_2a() {
        //④　ウインドウタイトルの設定
        super("Project2021 ProKisoB");
        
        //　自分自身（this） を、どこからでも呼び出せるように myFrame に代入しておきます。
        myFrame = this;
        //
        // このアプリケーションが持つ描画面を受け取り、gという名前で呼び出せるようにします。
        g = myFrame.getGraphics();
        //
        // Graphics2Dの機能を使う場合は、g2として呼び出せるようにします。
        g2 = (Graphics2D) g;
        
        setBackground(new Color(0,0,0));
        //
        //　用意しておいた10枚の画像を順番に画像用配列img[]に読み込み、
        //プログラム中で利用できるようにしています。
        // 　ファイル名は　T0.GIF～T9.GIF　ですが、for文で数字部分を変えながら
        // ファイル名を指定しています。
        Toolkit tk = getToolkit();
        img1 = new Image[11];
        img2=new Image[4];
        for (int i = 0; i < 11; i++) {
            img1[i] = tk.getImage("src/images/syougi" + i + ".PNG");}
        
        for(int i=0;i<4;i++){
            img2[i]=tk.getImage("src/images/ban"+i+".PNG");}
        
        //　用意しておいた3つの音を順番に音の格納用配列に読み込み、
        // プログラム中で利用できるようにしています。
        int numSounds = 1;
        clip = new Clip[numSounds];
        
        for (int i = 0; i < numSounds; i++) {
        clip[i] = ClipProB.createClip(new File("src/sounds/ss" + i + ".wav"));        }
       bt1=new Button("1");
       bt2=new Button("２");
       bt3=new Button("３");
       bt4=new Button("こちら将棋棋譜判定アプリです。");
       bt5=new Button("( , )");
       bt6=new Button("将棋とは");
       bt7=new Button("ここがすごい!!あの最年少棋士");
       bt8=new Button("次の一手集１");
       bt9=new Button("次の一手集２");
       bt1.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
                red=30;green=60;blue=110;
                setBackground(new Color(red,green,blue));
                repaint();
           }
       });
       bt2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                red=128;green=128;blue=128;
                setBackground(new Color(red,green,blue));
                repaint();
            }
       });
       bt3.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               red=20;green=130;blue=20;
               setBackground(new Color(red,green,blue));  
               repaint();
       }
       } );
      
       bt4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                bt4.setLabel("将棋棋譜判定アプリへようこそ");
            }
       });
       bt5.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               
           }
                   });
       bt6.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
              
           }
                   });
       bt7.addActionListener(new ActionListener(){
           public void actionPerformed (ActionEvent ae){
               
           }
                   });
       bt8.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               
           }
        });
       bt9.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               
           }
        });
    Panel pn1=new Panel();
    Panel pn2=new Panel();
    this.add(pn1,BorderLayout.EAST);
    this.add(pn2,BorderLayout.SOUTH);
    pn1.add(bt1);
    pn1.add(bt2);
    pn1.add(bt3);
    pn2.add(bt4);
    pn2.add(bt5);
    
        ///////////////////////////////////////////////////////////////////////////
        // (1)マウスの瞬間動作に反応する「イベントリスナー」MouseListenerの登録
        ///////////////////////////////////////////////////////////////////////////
        this.addMouseListener(new MouseAdapter(){
            // ①マウスがクリックされたときの処理
            public void mouseClicked(MouseEvent e) {
                
                //マウスの位置に画像を描画しています。
                //また、描画のたびに画像番号を切り替えています）。
                Graphics g = myFrame.getGraphics();
                Mx = e.getX();
                My = e.getY();
                int kihuX=0,kihuY=0;
                if(310<=e.getX()&&e.getX()<=940&&120<=e.getY()&&e.getY()<=840){
                if((e.getX()>=310)&&(e.getX()<380)){
                    kihuX=9;
                }
                if((e.getX()>=380)&&(e.getX()<450)){
                    kihuX=8;
                }
                if((e.getX()>=450)&&(e.getX()<520)){
                    kihuX=7;
                }
                if((e.getX()>=520)&&(e.getX()<590)){
                    kihuX=6;
                }
                if((e.getX()>=590)&&(e.getX()<630)){
                    kihuX=5;
                }
                if((e.getX()>=630)&&(e.getX()<700)){
                    kihuX=4;
                }
                if((e.getX()>=700)&&(e.getX()<770)){
                    kihuX=3;
                }
                if((e.getX()>=770)&&(e.getX()<840)){
                    kihuX=2;
                }
                if((e.getX()>=840)&&(e.getX()<920)){
                    kihuX=1;
                }
                if((e.getY()>=120)&&(e.getY()<200)){
                    kihuY=1;
                }
                if((e.getY()>=200)&&(e.getY()<280)){
                    kihuY=2;
                }
                if((e.getY()>=280)&&(e.getY()<360)){
                    kihuY=3;
                }
                if((e.getY()>=360)&&(e.getY()<440)){
                    kihuY=4;
                }
                if((e.getY()>=440)&&(e.getY()<520)){
                    kihuY=5;
                }
                if((e.getY()>=520)&&(e.getY()<600)){
                    kihuY=6;
                }
                if((e.getY()>=600)&&(e.getY()<680)){
                    kihuY=7;
                }
                if((e.getY()>=680)&&(e.getY()<760)){
                    kihuY=8;}
                
                if((e.getY()>=760)&&(e.getY()<840)){
                    kihuY=9;}
                
                System.out.println("("+kihuX+","+kihuY+")");
                g.drawString("("+kihuX+","+kihuY+")", Mx, My);
                bt5.setLabel("("+kihuX+","+kihuY+")");
                m++;
                m = (m > 10) ? 0 : m;
                g.drawImage(img1[m],e.getX(),e.getY(),myFrame);
                }
                
               
                if(90<=e.getX()&&e.getX()<=150&&100<=e.getY()&&e.getY()<=120){
                    g.setColor(new Color(0,128,128));
                    g.fillRoundRect(300,200,900,400,60,60);
                    g.setColor(new Color(0,0,0));
                    Font f3=new Font("Dialog",Font.BOLD,20);
                    g.setFont(f3);
                    g.drawString("将棋とは", 350, 240);
                    Font f4=new Font("Timesroman",Font.BOLD,12);
                    g.drawString("将棋は将棋駒と将棋盤を使って二人で行うボードゲームである。",310,280);
                    g.drawString("交互に駒を進めて一番強いとされる王将を取った人が勝ちとなる",310,310);
                    g.drawString("将棋には考えることの楽しさが魅力としてある",310,340);
                    g.drawString("自分がどのように攻めて、どのように守り、どのように相手の陣地を崩していくか",310,370);
                    g.drawString("相手のこの一手が何のためにあるのか、自分がこのように打てば、相手がどのように",310,400);
                    g.drawString("かかってくるのかを考える。対局が進めば進むほど将棋がどのように進んでいくか",310,430);
                    g.drawString("分からない。自分で考えて局面を進める。思考ゲームのようなものである。",310,460);
                    g.drawString("また礼儀正しく行われるところも魅力としてある。",310,490);
                    g.drawString("礼で始まり礼で終わるゲームとなっている。私はそこに荘厳さがあると考えられる。",310,520);
                }
                
                if(90<=e.getX()&&e.getX()<=270&&140<=e.getY()&&e.getY()<=160){
                    
                    g.setColor(new Color(0,128,128));
                    g.fillRoundRect(300,200,900,500,60,60);
                    g.setColor(new Color(0,0,0));
                    Font f3=new Font("Dialog",Font.BOLD,20);
                    g.setFont(f3);
                    g.drawString("ここがすごい‼あの最年少棋士", 350, 240);
                    Font f4=new Font("Timesroman",Font.BOLD,12);
                    g.drawString("このコラムで紹介するのは将棋の魅力を伝えるには欠かせない藤井聡太竜王",310,280);
                    g.drawString("私が彼を知ったのは、中学三年の時、ニュースで連勝記録を見て羽生さんや",310,310);
                    g.drawString("渡辺さんなど将棋界で強いとされている有名な人に勝てるほどの実力があり、",310,340);
                    g.drawString("自分は藤井聡太さんのような二将棋に強くなりたいと思うようになった。",310,370);
                    g.drawString("彼が強い理由には頭の回転の速さにある。",310,400);
                    g.drawString("藤井さんは何パターンなんて先もの状況を読み、頭の中で自分の中で最適な答えを",310,430);
                    g.drawString("出している。自分と変わらない年齢で頭の回転は速い彼を見て自分も将棋に",310,460);
                    g.drawString("強くなりたい思った。彼の頭の速さなら対局でわかる。対局では自分、周りの人",310,490);
                    g.drawString("または人間を超えたAIでさえ思いつかない、驚くような手を指している。",310,520);
                    g.drawString("その手が悪手（将棋で自分が不利になるような悪い手）でもなく",310,550);
                    g.drawString("自分の状況が良い方向に向かっている。",310,580);
                    g.drawString("コラム｛次の一手集１」「次の一手集２」には", 310, 610);
                    g.drawString("実際に藤井さんが対局で指した局面を紹介している",310,640);}
                int swion1=0,swion2=0;
                if(90<=e.getX()&&e.getX()<=170&&180<=e.getY()&&e.getY()<=220){
                   
                    if(swion1==0){
                        g.drawImage(img2[0],280,100 ,myFrame );
                        swion1++;
                }
                    if(swion1==1){
                        g.drawImage(img2[2],280,100,myFrame);
                        swion1--;
                    }}
                if(90<=e.getX()&&e.getX()<=170&&220<=e.getY()&&e.getY()<=240){
                    if(swion2==0){
                        g.drawImage(img2[1],280,100,myFrame);
                        swion2++;}
                    if(swion2==1){
                        g.drawImage(img2[3],280,100,myFrame);
                        swion2--;}              
                }
                if(90<=e.getX()&&e.getX()<=170&&260<=e.getY()&&e.getY()<=280) {
                    repaint();
                }   
              
                
                                    

                
                /*m++;
                m = (m > 10) ? 0 : m;
                g.drawImage(img1[m],e.getX(),e.getY(),myFrame);*/
                }
                
               
            
                

            //　②マウスカーソルが画面内に入ったときの処理
            public void mouseEntered(MouseEvent e){
                clip[0].loop(1);
                bt4.setLabel("将棋棋譜判定アプリへようこそ");
                
                System.out.println("将棋棋譜判定アプリへようこそ");
            }

            //　③マウスカーソルが画面から出たときの処理
            public void mouseExited(MouseEvent e){ 
                bt4.setLabel("将棋棋譜判定アプリです");
                bt5.setLabel("( ,)");
            }

            //　④マウスボタンが押されたときの処理
            public void mousePressed(MouseEvent e) {
                Graphics g=myFrame.getGraphics();
                
                /*int swion1=0,swion2=0;
                clip[1].loop(1);*/
            }
                
 
                
                
            

            //　⑤マウスボタンを放したときの処理
            public void mouseReleased(MouseEvent e) {
                bt4.setLabel("将棋棋譜判定アプリへようこそ");
            }
        });

        ///////////////////////////////////////////////////////////////////////////
        // (2)マウスの連続動作に反応する「イベントリスナー」MouseMotionListenerの登録
        ///////////////////////////////////////////////////////////////////////////
        this.addMouseMotionListener(
                new MouseMotionAdapter(){ 
            //　⑥ドラッグしたときの処理
            @Override
            public void mouseDragged(MouseEvent e){
                //マウスの位置に画像を描画しています。
                //また、描画のたびに画像番号を切り替えています）。
                Graphics g = myFrame.getGraphics();
                m++;
                m = (m > 10) ? 0 : m;
                g.drawImage(img1[m], e.getX(), e.getY(), myFrame);}
            

            //　⑦マウスを動かしたときの処理
            @Override
            public void mouseMoved(MouseEvent e) {
                Graphics g=getGraphics();
                 /*if(90<=e.getX()&&e.getX()<=110&&60<=e.getY()&&e.getY()<=80){
                    
                 }
                if(90<=e.getX()&&e.getX()<=110&&100<=e.getY()&&e.getY()<=120){
                    g.fillRect(400,500,500,500);}
                
                if(90<=e.getX()&&e.getX()<=270&&140<=e.getY()&&e.getY()<=160){
                    g.fillRect(200,700,500,500);
                }
                int swion1=0,swion2=0;
                if(90<=e.getX()&&e.getX()<=110&&180<=e.getY()&&e.getY()<=220){
                   
                    if(swion1==0){
                        g.drawImage(img2[0],280,100 ,myFrame );
                        swion1++;
                }
                    if(swion1==1){
                        g.drawImage(img2[2],280,100,myFrame);
                    }}
                if(90<=e.getX()&&e.getX()<=110&&220<=e.getY()&&e.getY()<=240){
                    if(swion2==0){
                        g.drawImage(img2[1],280,100,myFrame);
                        swion2++;}
                    if(swion2==1){
                        g.drawImage(img2[3],280,100,myFrame);
                        swion2--;}
                    
                }*/
                                    

                
                /*if(90<=e.getX()&&e.getX()<=110&&260<=e.getY()&&e.getY()<=280){
                    g.setColor(new Color(0,0,0));
                    repaint();}*/
                
                }
        });

        //⑤　ウィンドウを閉じるための動作の設定
        addWindowListener(new SampleWindowListner());
        //⑥　ウィンドウの表示サイズの設定
        setSize(1200, 900);
        //⑦　ウィンドウ部品（Frame)を表示させる（指示しないと表示されない）
        setVisible(true);
        //⑧　表示のための処理　⑨へ
}

    //////////////////////////////////////////////////////////////////////////////////////
    // 背景として描いておきたい画像や図形は、paintメソッドの中で描いておきましょう。
    //　マウスのイベント処理の中からpaintメソッドを呼びたいときは、直接は呼ばずに、
    //　repaint();　という命令で間接的に呼びます。
    //　ただし、そのときには、それまでの画面は一度消されてから、描き直しが行われます。
    public void paint(Graphics g) {
        
        this.g = g;
        g.setColor(new Color(240,190,140));
        g.fillRect(280, 100, 680, 770);
        g.drawImage(img1[1],250,30,this);
        g.drawImage(img1[1], 850, 30, this);
        Font f1=new Font("Dialog",Font.BOLD,20);
        g.setFont(f1);
        g.setColor(new Color(255,255,255));
        g.drawString("==プログラミング基礎演習B 最終プロジェクト課題2021==",300, 58);
        g.drawLine(300,60,880,60);
        g.drawString("テーマ:　将棋", 780,90 );
        g.fillRect(90, 60, 60,20);
        g.fillRect(90,100,60,20 );
        g.fillRect(90,140,180,20);
        g.fillRect(90,180, 80,20);
        g.fillRect(90,220,80,20);
        g.fillRect(90,260,80,20);
        Font f2=new Font("Timesroman",Font.BOLD,12);
        g.setFont(f2);
        g.drawString("クリックすると、背景が変わります↗",740,100);
        g.setColor(Color.BLACK);
        g.drawString("コラム", 95,75 );
        g.drawString("将棋とは",95,115);
        g.drawString("ここがすごい!!あの最年少棋士",95,155);
        g.drawString("次の一手集１",95,195);
        
        g.drawString("次の一手集２",95,235);
        g.drawString("リセット",95,275);
        g.setColor(new Color(100,100,100));
                
        
        for(int i=0;i<10;i++){
            g.drawLine(310, 120+80*i, 940, 120+80*i);
        }
        for(int i=0;i<10;i++){
            g.drawLine(310+70*i,120,310+70*i,840);
        }
    }
   
    //　ウィンドウを閉じる動作で行う処理（変更不要）
    class SampleWindowListner extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        
        }
               
    }
        }              