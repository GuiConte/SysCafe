
package sys_cafe;

import java.sql.SQLException;
import java.text.ParseException;


public class Main {

    public static void main(String[] args) throws SQLException, ParseException {
         
        try {
            com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme("Blue", "", "Sys_cafe");
            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Menu c = new Menu();
        c.desenharTela();
    }
}

