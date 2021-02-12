package com.tank;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class TankJoinMsg {
    int x,y;
    DIR dir;
    boolean moving;
    Group group;
    UUID id;

    public TankJoinMsg() {
    }

    public TankJoinMsg(int x, int y, DIR dir, boolean ismoving, Group group, UUID uuid) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = ismoving;
        this.group = group;
        this.id = uuid;
    }

    public byte[] toBytes(){
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try{
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);

            //write
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(baos!=null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(dos!=null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }
}
