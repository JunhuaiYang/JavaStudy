package application;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BRXX {
	public String BRBH=null;
	public String BRMC=null;
	public String PYZS=null;	
    public String DLKL=null;
    public double YCJE=0;  //不能随便使用，如果病人同时登录两台电脑挂号
    
    public BRXX(String BRBH, String BRMC, String PYZS, String DLKL, double YCJE) {
    	this.BRBH=new String(BRBH);
    	this.BRMC=new String(BRMC);
    	this.PYZS=new String(PYZS);    	
    	this.DLKL=new String(DLKL);
    	this.YCJE=YCJE;
    }
    public BRXX(ResultSet rsbr) {
    	try {
    	    this.BRBH=new String(rsbr.getString("BRBH"));
    	    this.BRMC=new String(rsbr.getString("BRMC"));
    	    this.PYZS=new String(rsbr.getString("PYZS"));    	
    	    this.DLKL=new String(rsbr.getString("DLKL"));
    	    this.YCJE=rsbr.getDouble("YCJE");
    	}
    	catch(SQLException e) {
    		
    	}
    }
}
