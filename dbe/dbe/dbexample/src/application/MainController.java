package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent ;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class MainController  implements Initializable {
	private Main myApp;			//用于和主控程序关联
	//定义界面中的输入栏目
	@FXML
	private ComboBox<String> yhlb;
	@FXML 
    private ComboBox<String> yhzh;
    @FXML    //不能删除：每个@FXML只管一个控件
    private PasswordField yhkl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//yhlb.getItems().removeAll(yhlb.getItems());
    	yhlb.getItems().addAll( "病人登录", "医生登录");
    	//yhzh.getItems().addAll( "xx病人登录", "xx医生登录"); 
    	//yhzh.setOnMouseEntered(e -> onyhzhEnter(yhzh, e)); 	//去掉注解后用第1种方法通过Lambda表达式处理事件
    	//yhzh.setOnMouseExited(e -> onyhzhExit(yhzh, e));		//去掉注解后用第1种方法通过Lambda表达式处理事件
    	yhzh.setVisibleRowCount(5);
    }   
    
      
    //第1种方法通过Lambda表达式处理事件
    private void onyhzhEnter(ComboBox<String> yhzh, MouseEvent event)  { //可不要yhzh参数
    	yhzh.getItems().removeAll(yhzh.getItems());
    	int len=myApp.brxx.size();
    	for(int x=0; x<len; x++)
    		yhzh.getItems().add(myApp.brxx.get(x).BRBH+"\t"+myApp.brxx.get(x).BRMC+"\t"+myApp.brxx.get(x).PYZS);
    	//yhzh.getItems().addAll( "xx病人登录", "xx医生登录","xx病人登录"); 
    	yhzh.show();
    }  
    
    private void onyhzhExit(ComboBox<String> yhzh, MouseEvent event)  {
    	yhzh.hide();
    }   
    
    //第2种方法通过JavaFX的Scenebuilder表达式处理事件
    @FXML
    private void onyhEnter(MouseEvent event)  {
    	yhzh.getItems().removeAll(yhzh.getItems());
    	int len=myApp.brxx.size();
    	for(int x=0; x<len; x++)
    		yhzh.getItems().add(myApp.brxx.get(x).BRBH+"\t"+myApp.brxx.get(x).BRMC+"\t"+myApp.brxx.get(x).PYZS);
    	//yhzh.getItems().addAll( "xx病人登录", "xx医生登录","xx病人登录"); 
    	yhzh.show();
    } 

    @FXML
    private void onyhExit(MouseEvent event)  {
    	yhzh.hide();  
    }   
    
    @FXML
    private void onkeyPre(KeyEvent event)  {
    	//利用最大公共字串算法求输入串s1=yhzh.getValue()和拼音字首字符串s2=myApp.brxx.get(x).PYZS的最大公共字串得到s3;
    	//求s1和s2的相似度sim=2.0*s3/(s1+s2),然后按sim自高至低排序过滤显示，总是以排到第一的作为默认账号输入
    	
    } 

    @FXML
    private void onqueding(ActionEvent event)  {
    	//加载主控界面
		//装载登录界面
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dbgui.fxml"));  //假定dbgui是主界面
        Parent root =null;
		try {
		    root = (Parent)loader.load();
		}
	    catch(Exception e) {
		    e.printStackTrace();
	    }
		DbguiController controller=(DbguiController ) loader.getController();
        controller.setUp(myApp);
		Scene scene = new Scene(root,800,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		myApp.primaryStage.setScene(scene);  //登录后，主控界面可以利用登录界面的Stage，主控界面打开菜单弹出的新界面可以创建新的Stage
		myApp.primaryStage.show();    	
    } 
    
    public void setUp(Main application) {
        myApp = application;

    }    
}
