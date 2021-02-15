package com.tank;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


public class Client {
    //singleton
    public static final Client INSTANCE = new Client();
    private Client(){
    }

    private Channel channel = null;

    public void connect() {
        EventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        try{
            ChannelFuture future = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost", 8888);

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(!future.isSuccess())
                        System.out.println("not connected");
                    else {
                        System.out.println("connected");
                        channel = future.channel();
                    }
                }
            });

            future.sync();

            future.channel().closeFuture().sync();

            System.out.println("connection closed");

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void send(TankJoinMsg msg) {
        System.out.println("SEND:" + msg);
        channel.writeAndFlush(msg);
    }

    public void closeConnect() {
		/*this.send("_bye_");
		//channel.close();
*/	}
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new TankJoinMsgEncoder())
                .addLast(new TankJoinMsgDecoder())
                .addLast(new ClientHandler());
    }
}

class ClientHandler extends SimpleChannelInboundHandler<TankJoinMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TankJoinMsg msg) throws Exception {
        if(msg.id.equals(TankFrame.INSTANCE.getMainTank().getid())||TankFrame.INSTANCE.findbyUUID(msg.id)!=null) return;
        Tank tank = new Tank(msg);
        TankFrame.INSTANCE.addTank(tank);
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }
}