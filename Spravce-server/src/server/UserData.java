/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/**
 *
 * @author beretis
 */
public class UserData
{
    public static Hashtable data;
    
    public UserData(Hashtable data)
    {
        this.data = data;
    }
// s mapou bude pracovat server v triede Protocol
    public Hashtable getData()
    {
        return data;
    }

    public static void setData(Hashtable data)
    {
        UserData.data = data;
    }
    
    
    
}
