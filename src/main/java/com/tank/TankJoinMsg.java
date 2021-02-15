package com.tank;

import java.io.*;
import java.util.UUID;

public class TankJoinMsg extends Msg {

    int x, y;
    DIR dir;
    boolean moving;
    Group group;
    UUID id;

    public TankJoinMsg() {
    }

    public TankJoinMsg(Tank tank) {
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.dir;
        this.moving = tank.moving;
        this.group = tank.getGroup();
        this.id = tank.getid();
    }


    public TankJoinMsg(int x, int y, DIR dir, boolean moving, Group group, UUID uuid) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = uuid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public DIR getDir() {
        return dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public Group getGroup() {
        return group;
    }

    public UUID getId() {
        return id;
    }


    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);

            //write msgType

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }
    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = DIR.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.group = Group.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(),dis.readLong());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankJoin;
    }

    @Override
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", moving=" + moving +
                ", group=" + group +
                ", id=" + id +
                '}';
    }

    @Override
    public void handle() {
        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getid()) || TankFrame.INSTANCE.findbyUUID(this.id) != null)
            return;
        Tank tank = new Tank(this);
        TankFrame.INSTANCE.addTank(tank);
        Client.INSTANCE.send(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }


}
