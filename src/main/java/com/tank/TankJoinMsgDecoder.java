package com.tank;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

public class TankJoinMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes()<33) return;
        TankJoinMsg msg = new TankJoinMsg();
        msg.x = byteBuf.readInt();
        msg.y = byteBuf.readInt();
        msg.dir = DIR.values()[byteBuf.readInt()];
        msg.moving = byteBuf.readBoolean();
        msg.group = Group.values()[byteBuf.readInt()];
        msg.id = new UUID(byteBuf.readLong(),byteBuf.readLong());
        list.add(msg);
    }
}
