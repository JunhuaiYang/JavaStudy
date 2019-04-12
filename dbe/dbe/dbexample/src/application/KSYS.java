package application;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KSYS {
	public String YSBH=null;
	public String YSMC=null;
	public String PYZS=null;
    public String DLKL=null;
    public int  SFZJ=0;

    public KSYS(String YSBH, String BRMC,String PYZS, String DLKL, short SFZJ) {
    	this.YSBH=new String(YSBH);
    	this.YSMC=new String(YSMC);
    	this.PYZS=new String(PYZS);
    	this.DLKL=new String(DLKL);
    	this.SFZJ=SFZJ;
    }
    public KSYS(ResultSet rsys) {
    	try {
    	    this.YSBH=new String(rsys.getString("YSBH"));
    	    this.YSMC=new String(rsys.getString("YSMC"));
    	    this.PYZS=new String(rsys.getString("PYZS"));    	
    	    this.DLKL=new String(rsys.getString("DLKL"));
    	    this.SFZJ=rsys.getInt("SFZJ");
    	}
    	catch(SQLException e) {
    		
    	}
    }    
}
