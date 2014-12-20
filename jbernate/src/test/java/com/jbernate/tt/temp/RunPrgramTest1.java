package com.jbernate.tt.temp;




public class RunPrgramTest1 {

	public static void main(String[] args) {
		System.out.println( "start" );
		String command = "cmd /c start C:\\CMBO_IF\\CMBO_IF.exe 0001 020 S000092 0001 % 1 unixpro";
		
		try{
			//Process child = Runtime.getRuntime().exec(command);
			
			//child.waitFor();
			//Desktop.getDesktop().open( new File( "C:/WINDOWS/System32/cmd.exe /c start C:\\CMBO_IF\\CMBO_IF.exe 0001 020 S000092 0001 % 1 unixpro" ) );
			
			new ProcessBuilder( "cmd", "/c", "C:\\CMBO_IF\\CMBO_IF.exe", "0001", "020", "S000092", "0001", "%", "1", "unixpro" ).start();
		}catch( Exception e ){ e.printStackTrace(); }
		
		System.out.println( "end" );
	}

}