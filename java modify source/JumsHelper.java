package jums;

/**
 * ��ʌn�̏�����\�����ȗ������邽�߂̃w���p�[�N���X�ł��B�萔�Ȃǂ��ۑ�����܂�
 * @author hayashi-s
 */
public class JumsHelper {
    
    //�g�b�v�ւ̃����N��萔�Ƃ��Đݒ�
    private final String homeURL = "index.jsp";
    
    public static JumsHelper getInstance(){
        return new JumsHelper();
    }
    
    //�g�b�v�ւ̃����N��ԋp
    public String home(){
        return "<a href=\""+homeURL+"\">�g�b�v�֖߂�</a>";
    }
}
