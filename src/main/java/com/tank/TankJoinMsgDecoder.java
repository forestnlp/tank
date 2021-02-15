package com.tank;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class TankJoinMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        //这个是消息头，消息类型4字节+消息长度4字节
        if(in.readableBytes()<8) return;
        //移到标记处
        in.markReaderIndex();
        //msgType
        MsgType msgType = MsgType.values()[in.readInt()];
        //len
        int len = in.readInt();
        //check len
        if(in.readableBytes()<len){
            in.resetReaderIndex();
            return;
        }
        //read
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        //parse
        switch (msgType){
            case TankJoin:
                TankJoinMsg tankJoinMsg = new TankJoinMsg();
                tankJoinMsg.parse(bytes);
                out.add(tankJoinMsg);
                break;
            default:
                break;
        }
    }
}
