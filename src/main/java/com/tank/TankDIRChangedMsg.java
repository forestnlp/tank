package com.tank;

import java.io.*;
import java.util.UUID;



public class TankDIRChangedMsg extends Msg {
	
	UUID id;
	DIR DIR;

	int x, y;

	public TankDIRChangedMsg() {
		
	}

	public TankDIRChangedMsg(Tank t) {
		this.id = t.getid();
		this.DIR = t.dir;
		this.x = t.getX();
		this.y = t.getY();
	}

	public TankDIRChangedMsg(UUID id, int x, int y , DIR DIR) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.DIR = DIR;
	}

	public DIR getDIR() {
		return DIR;
	}

	public UUID getid() {
		return id;
	}

	@Override
	public MsgType getMsgType() {
		return MsgType.TankDirChanged;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public void handle() {
		if (this.id.equals(TankFrame.INSTANCE.getMainTank().getid()))
			return;

		Tank t = TankFrame.INSTANCE.findTankByUUID(this.id);

		if (t != null) {
			t.setMoving(true);
			t.setX(this.x);
			t.setY(this.y);
			t.setDir(this.DIR);
		}
	}
	
	@Override
	public void parse(byte[] bytes) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
		try {
			
			this.id = new UUID(dis.readLong(), dis.readLong());
			this.x = dis.readInt();
			this.y = dis.readInt();
			this.DIR = DIR.values()[dis.readInt()];
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void setDIR(DIR DIR) {
		this.DIR = DIR;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public byte[] toBytes() {
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null; 
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);
			dos.writeLong(id.getMostSignificantBits());
			dos.writeLong(id.getLeastSignificantBits());
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(DIR.ordinal());
			dos.flush();
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(dos != null) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return bytes;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName())
			   .append("[")
			   .append("uuid=" + id + " | ")
			   .append("x=" + x + " | ")
			   .append("y=" + y + " | ")
			   .append("DIR=" + DIR + " | ")
			   .append("]");
		return builder.toString();
	}
}
