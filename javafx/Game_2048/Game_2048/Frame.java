
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	import java.util.Random;
	public class Frame extends JFrame
	{
			//original
		    private static int[][] block_Data = new int[4][4];
		  //定义newData数组用于暂存处理中的数组
		    //reverse and transpose
		  	private static int[][] newData=new int[4][4];
		  //定义newData2数组用于暂存移动前中的数组
		  	//
		  	private static int[][] newData2=new int[4][4];
		   
		  	private static JLabel[][] block_Lable = new JLabel[4][4];	//与数组对应的标签
		//构造函数
		public Frame()
		{
			//初始化窗体大小位置等
			initBasic();
			//初始化空块
			initEmptyBlocks();
			//初始化两个初始值  到block_Data数组中，并设置block_Label中对应块的图像
			initData();
			//鼠标监听，设置窗口可关闭
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			});
			//键盘监听，获取方向键执行操作
			this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent event)
			{    int k =event.getKeyCode();
			//暂时先存好移动前的数组
			   for(int m=0; m<4; m++)
			      {  for(int n=0; n<4; n++)
				     {newData2[m][n]=block_Data[m][n];}
			      }
				switch(k)
				{
				case 37:left(); break;
				case 38:up();   break;
				case 39:right();break;
				case 40:down(); break;
				}
				//yesornoMove();
				//若数组某一方向移动后没有变化则不产生新数字,反之产生新数
				boolean havemove = yesornoMove();
				if(havemove==true)
				{createOneRandomNumber();}
				upDateUI(block_Data);
				gamestate();
			}
		});
			//可视化
			setVisible(true);
		}
		
		private void initBasic()
		{
			this.setTitle("Game2048");	
			this.setSize(750, 800);		//设置窗体大小
			this.setLocation(700, 100);	//设置窗体显示位置
			this.setLayout(null);		//设置窗体布局方式为自由布局方式（自由布局就可以按照像素位置去放置组件）
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);	//设置窗体x为默认关闭窗口
		}	
		private void initEmptyBlocks()
		{
			//设置16个空块，设置每个块的位置，将块添加到主界面中
			for(int i=0; i<4; i++)
			{
				for(int j=0; j<4; j++)
				{
					//***JFrame中横纵轴于数组相反****
					block_Lable[j][i] = new JLabel(" ",JLabel.CENTER);//居中显示
					Font font = new Font("Verdana",Font.BOLD , 55);      //创建1个字体实例Font.PLAIN
					block_Lable[j][i].setFont(font);      //设置JLabel的字体
					block_Lable[j][i].setForeground(Color.darkGray);       //设置文字的颜色
					block_Lable[j][i].setBounds(10+i*180, 10+j*180, 160, 160);
					block_Lable[j][i].setOpaque(true); 
					block_Lable[j][i].setBackground(Color.lightGray);
					this.add(block_Lable[j][i]);
				}
			}
		}
		//游戏开始时需调用两次生成随机块的方法
		public static void initData()
			{
				for(int n=0; n<2; n++)
				{
					createOneRandomNumber();
				}
			}
			//由于每次移动后，都要生成一个随机块，所以单独定义一个方法来生成随机块
			//将生成的数存放到block_Data
		public static void createOneRandomNumber()
			{
				int i,j;
				Random random = new Random();					//0到4之间的随机int值，包含0而不包含4,直到二维数组中有个元素为0
				i = random.nextInt(4);
				j = random.nextInt(4);
				while(true)
				{
					if(block_Data[i][j] == 0)
					{
						break;
					}
					else
					{
						i = random.nextInt(4);			//注意是0-3的随机数的表达方式
						j = random.nextInt(4);
					}
				}
				block_Data[i][j]=2;
				if(block_Data[i][j]!=0)
					block_Lable[i][j].setText(block_Data[i][j]+"");	//后面要加“”，不然报错
				else
				block_Lable[i][j].setText(" ");
			}
		//根据数据数组来刷新游戏界面，使视觉效果和真实数据是一致的，reFreshScore是在每次移动后刷新当前得分
		public static void upDateUI(int[][] block_Data)
			{
			
				for(int i=0; i<4; i++)
				{
					for(int j=0; j<4; j++)
					{
						//java JLable中不能写int类型 这里做个转换
						if(block_Data[i][j]!=0)
							block_Lable[i][j].setText(block_Data[i][j]+"");
							else
							block_Lable[i][j].setText(" ");
					}
				}
			}
		//重置每个块元素(数组和标签都清空)
		public static void reSetBlocks()
			{
				for(int i=0; i<4; i++)
				{
					for(int j=0; j<4; j++)
					{
						block_Data[i][j] = 0;
						if(block_Data[i][j]!=0)
							block_Lable[i][j].setText(block_Data[i][j]+"");
						else
							block_Lable[i][j].setText(" ");
					}
				}
			}
		//游戏状态函数（只判断是否输赢）
				public void gamestate()
				{
					//胜利
					for(int i=0; i<4; i++)
					{
						for(int j=0; j<4; j++)
						{
							if(block_Data[i][j] == 2048)
							{
								//确认对话框。提出问题，然后由用户自己来确认（按"Yes"或"No"按钮）
								int result = JOptionPane.showConfirmDialog(null, "You win.Again？", "Result",JOptionPane.YES_NO_OPTION);
								//选择YES重新开始游戏
								if(result == 0)
								{
									//先清空数据再初始化
									Frame.reSetBlocks();
									Frame.initData();
								}
								//选择NO则退出游戏程序
								else
								{
									System.exit(0);
								}
							}
						}
					}
					//失败
					if(block_DataIsFull() && !canMove())
					{
						int result = JOptionPane.showConfirmDialog(null, "You lose.Again？", "Result", JOptionPane.YES_NO_OPTION);
						if(result == 0)
						{
							Frame.reSetBlocks();
							Frame.initData();
						}
						else
						{System.exit(0);}
						//return;
					}
				}
				//空格是否以满函数
				private boolean block_DataIsFull()
				{
					for(int i=0; i<4; i++)
					{
						for(int j=0; j<4; j++)
						{
							if(block_Data[i][j] == 0)
							{
								return false;
							}
						}
					}
					return true;
				}
				//是否可移动函数（格子已满情况下）
				private boolean canMove()
				{
					//情况一 某元素的右边或下边的元素与该元素相等(左上角3*3数组)
					for(int i=0; i<3; i++)
					{
						for(int j=0; j<3; j++)
						{
							if(block_Data[i][j]==block_Data[i+1][j]||block_Data[i][j]==block_Data[i][j+1])
								return true;
						}
					}
					//单独考虑矩阵第4行和第4列的情况
					for(int i=0; i<3; i++)
					{
						//第4行左右相邻是否有相等的
						if(block_Data[3][i]==block_Data[3][i+1])
							return true;
						//第4列左右相邻是否有相等的
						if(block_Data[i][3]==block_Data[i+1][3])
							return true;
					}
					//以上情况均没有，即不可移动
					return false;
				}
		
				//判断是否移动函数
				private static boolean yesornoMove()
				{
					//若数组某一方向移动后没有变化则不产生新数字,反之产生新数
					for(int i=0; i<4; i++)
				      {  
						for(int j=0; j<4; j++)
					     {  if(block_Data[i][j]!=newData2[i][j])
					        return true; 
					     }
				      }
					return false;
				}
				//定义reverse，transpose，cover_up,merge函数来处理山下左右操作
				//矩阵每行都翻转函数
				public  static void reverse()
				{
					for(int i=0; i<=3; i++)
						for(int j=0; j<=3; j++)
							newData[i][j]=block_Data[3-i][3-j];
					
					for(int i=0; i<4; i++)
						for(int j=0; j<4; j++)
							block_Data[i][j]=newData[i][j];	
				}
				//矩阵转置函数(行列交换)
				public static void transpose()
				{
					for(int i=0; i<4; i++)
						for(int j=0; j<4; j++)
							newData[i][j]=block_Data[j][i];
					for(int i=0; i<4; i++)
						for(int j=0; j<4; j++)
							block_Data[i][j]=newData[i][j];	
				}
				//移动覆盖函数(有0就移动并覆盖**默认左移**)
				public static void coverup()
				{
				for(int k=0;k<=2;k++)				//最多只需3次覆盖
					for(int i=0; i<4; i++)
						for(int j=0; j<3; j++)		//第四列为0不需操作故不用考虑
							if(block_Data[i][j]==0)
							{
								block_Data[i][j]=block_Data[i][j+1];
								block_Data[i][j+1]=0;
							}
					
				}
				//合并函数，每行有相同的就加起来**默认左移**
				//放到靠左的元素里，靠右一个位置置0,默认coverup函数已执行
				public static void merge()
				{
					for(int i=0; i<4; i++)
						for(int j=0; j<3; j++)
						if(block_Data[i][j]==block_Data[i][j+1]&&block_Data[i][j]!=0)
							{
							block_Data[i][j]=block_Data[i][j]*2;
							block_Data[i][j+1]=0;
							}
				}
				public static void up()
				{
					transpose();
					coverup();
					merge();
					coverup();
					transpose();
				}
		        public static void down()
				{
		             transpose();
		             reverse();
		             coverup();
		        	 merge();
		        	 coverup();
		        	 reverse();
					 transpose();
				}
		        public static void left()
				{
		        	coverup();
		        	merge();
		        	coverup();
				}
		        public static void right()
				{
		        	reverse();
		        	coverup();
		        	merge();
		        	coverup();
		        	reverse();
				}
	}

