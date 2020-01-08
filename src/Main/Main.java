package Main;


import Produit.FormProduit;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{


	  @Override
	    public void start(Stage primaryStage) throws Exception {
			FormProduit FP = new FormProduit();
        	
            
			Stage window = new Stage();
			window.setScene(FP.scene);
			try {
				FP.start(window);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    public static void main(String[] args) {
	        launch();
	    }
	
}
