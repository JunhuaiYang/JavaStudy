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
	private Main myApp;			//���ں����س������
	//��������е�������Ŀ
	@FXML
	private ComboBox<String> yhlb;
	@FXML 
    private ComboBox<String> yhzh;
    @FXML    //����ɾ����ÿ��@FXMLֻ��һ���ؼ�
    private PasswordField yhkl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//yhlb.getItems().removeAll(yhlb.getItems());
    	yhlb.getItems().addAll( "���˵�¼", "ҽ����¼");
    	//yhzh.getItems().addAll( "xx���˵�¼", "xxҽ����¼"); 
    	//yhzh.setOnMouseEntered(e -> onyhzhEnter(yhzh, e)); 	//ȥ��ע����õ�1�ַ���ͨ��Lambda����ʽ�����¼�
    	//yhzh.setOnMouseExited(e -> onyhzhExit(yhzh, e));		//ȥ��ע����õ�1�ַ���ͨ��Lambda����ʽ�����¼�
    	yhzh.setVisibleRowCount(5);
    }   
    
      
    //��1�ַ���ͨ��Lambda����ʽ�����¼�
    private void onyhzhEnter(ComboBox<String> yhzh, MouseEvent event)  { //�ɲ�Ҫyhzh����
    	yhzh.getItems().removeAll(yhzh.getItems());
    	int len=myApp.brxx.size();
    	for(int x=0; x<len; x++)
    		yhzh.getItems().add(myApp.brxx.get(x).BRBH+"\t"+myApp.brxx.get(x).BRMC+"\t"+myApp.brxx.get(x).PYZS);
    	//yhzh.getItems().addAll( "xx���˵�¼", "xxҽ����¼","xx���˵�¼"); 
    	yhzh.show();
    }  
    
    private void onyhzhExit(ComboBox<String> yhzh, MouseEvent event)  {
    	yhzh.hide();
    }   
    
    //��2�ַ���ͨ��JavaFX��Scenebuilder����ʽ�����¼�
    @FXML
    private void onyhEnter(MouseEvent event)  {
    	yhzh.getItems().removeAll(yhzh.getItems());
    	int len=myApp.brxx.size();
    	for(int x=0; x<len; x++)
    		yhzh.getItems().add(myApp.brxx.get(x).BRBH+"\t"+myApp.brxx.get(x).BRMC+"\t"+myApp.brxx.get(x).PYZS);
    	//yhzh.getItems().addAll( "xx���˵�¼", "xxҽ����¼","xx���˵�¼"); 
    	yhzh.show();
    } 

    @FXML
    private void onyhExit(MouseEvent event)  {
    	yhzh.hide();  
    }   
    
    @FXML
    private void onkeyPre(KeyEvent event)  {
    	//������󹫹��ִ��㷨�����봮s1=yhzh.getValue()��ƴ�������ַ���s2=myApp.brxx.get(x).PYZS����󹫹��ִ��õ�s3;
    	//��s1��s2�����ƶ�sim=2.0*s3/(s1+s2),Ȼ��sim�Ը��������������ʾ���������ŵ���һ����ΪĬ���˺�����
    	
    } 

    @FXML
    private void onqueding(ActionEvent event)  {
    	//�������ؽ���
		//װ�ص�¼����
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dbgui.fxml"));  //�ٶ�dbgui��������
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
		myApp.primaryStage.setScene(scene);  //��¼�����ؽ���������õ�¼�����Stage�����ؽ���򿪲˵��������½�����Դ����µ�Stage
		myApp.primaryStage.show();    	
    } 
    
    public void setUp(Main application) {
        myApp = application;

    }    
}