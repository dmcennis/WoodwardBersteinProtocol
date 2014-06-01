package org.woodwardbernsteinprotocol.identity;

import java.io.*;

/**
 * Created by dmcennis on 5/31/2014.
 */
public class IdentityName implements Identity {
    String name;

    public IdentityName(){
        name = null;
    }

    public IdentityName(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentityName)) return false;

        IdentityName that = (IdentityName) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public void transmit(OutputStream stream) throws IOException {
        ObjectOutputStream writer = new ObjectOutputStream(stream);
        writer.writeObject(name);
    }

    @Override
    public void parse(InputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(stream);
        name = (String)reader.readObject();
    }
}
