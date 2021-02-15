import com.tank.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TankJoinMsgCodecTest {
    @Test
    void testEncoder(){
        //嵌入式的channel
        EmbeddedChannel channel = new EmbeddedChannel();
        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(26,32, DIR.UP,true, Group.good,id);
        //将Encoder放入
        channel.pipeline().addLast(new TankJoinMsgEncoder());
        //写消息
        channel.writeOutbound(msg);
        //读出消息
        ByteBuf buf = (ByteBuf)channel.readOutbound();
        //逐个读出
        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrd = buf.readInt();
        DIR dir = DIR.values()[dirOrd];
        boolean moving = buf.readBoolean();
        int groupOrd = buf.readInt();
        Group group = Group.values()[groupOrd];
        UUID uid = new UUID(buf.readLong(),buf.readLong());
        //断言
        assertEquals(26,x);
        assertEquals(32,y);
        assertEquals(DIR.UP,dir);
        assertEquals(Group.good,group);
        assertEquals(id,uid);
    }

    @Test
    void testDecoder(){
        EmbeddedChannel channel = new EmbeddedChannel();
        UUID uid = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(66,99,DIR.LEFT,false,Group.bad,uid);
        channel.pipeline().addLast(new TankJoinMsgDecoder());
        //将buff与坦克比较
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(msg.toBytes());
        //写buf
        channel.writeOutbound(buf.duplicate());
        //断言
        assertEquals(66,msg.getX());
        assertEquals(99,msg.getY());
        assertEquals(DIR.LEFT,msg.getDir());
        assertEquals(false,msg.isMoving());
        assertEquals(Group.bad,msg.getGroup());
        assertEquals(uid,msg.getId());
    }
}
