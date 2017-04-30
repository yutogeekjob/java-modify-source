package jums;

/**
 * ���[�U�[�����i�[����e�[�u���ɑ΂��Ă̑��쏈��������
 * DB�ڑ��n��DBManager�N���X�Ɉ�C
 * ��{�I�ɂ͂�肽��1��ނ̓���ɑ΂���1���\�b�h
 * @author hayashi-s
 */
import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
public class UserDataDAO {
    
    //�C���X�^���X�I�u�W�F�N�g��ԋp�����ăR�[�h�̊ȗ���
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    /**
     * �f�[�^�̑}���������s���B���ݎ����͑}�����O�ɐ���
     * @param ud �Ή������f�[�^��ێ����Ă���JavaBeans
     * @throws SQLException �Ăяo������catch�����邽�߂ɃX���[ 
     */
    public void insert(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,birthday,tell,type,comment,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setDate(2, ud.getBirthday());
            st.setString(3, ud.getTell());
            st.setInt(4, ud.getType());
            st.setString(5, ud.getComment());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
}
