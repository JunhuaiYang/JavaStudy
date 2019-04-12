package application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;


public class DbguiController implements Initializable{
	private Main myApp;			//用于和主控程序关联
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }  
    
    
    
    public void setUp(Main application) {
        myApp = application;

    }     
}
