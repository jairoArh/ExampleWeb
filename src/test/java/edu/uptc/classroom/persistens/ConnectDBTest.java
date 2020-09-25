/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uptc.classroom.persistens;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jairoarmando
 */
public class ConnectDBTest {
    
    private ConnectDB connect;
    
    private void setup( ){

        connect = new ConnectDB("jdbc:mysql://localhost:3306/colombia", "uptc", "estudiante" );
    }

    @Test
    public void testConnect( ) {
        setup();
        assertTrue( connect.getConnect( ) );
    }
    
}
