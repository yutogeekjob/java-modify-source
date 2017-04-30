package jums;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * insertresult�ƑΉ�����T�[�u���b�g
 * �t�H�[��������͂��ꂽ�l���Z�b�V�����o�R�Ŏ󂯎��A�f�[�^�x�[�X��insert����
 * ���ڃA�N�Z�X�����ꍇ��error.jsp�ɐU�蕪��
 * @author hayashi-s
 */
public class InsertResult extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //�Z�b�V�����X�^�[�g
        HttpSession session = request.getSession();
        
        try{String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("�s���ȃA�N�Z�X�ł�");
            }
            //���[�U�[���ɑΉ�����JavaBeans�I�u�W�F�N�g�Ɋi�[���Ă���
            UserDataDTO userdata = new UserDataDTO();
            userdata.setName((String)session.getAttribute("name"));
            Calendar birthday = Calendar.getInstance();
            userdata.setBirthday(birthday.getTime());
            userdata.setType(Integer.parseInt((String)session.getAttribute("type")));
            userdata.setTell((String)session.getAttribute("tell"));
            userdata.setComment((String)session.getAttribute("comment"));
            
            //DB�փf�[�^�̑}��
            UserDataDAO .getInstance().insert(userdata);
            
            request.getRequestDispatcher("/insertresult.jsp").forward(request, response);
        }catch(Exception e){
            //�f�[�^�}���Ɏ��s������G���[�y�[�W�ɃG���[����n���ĕ\��
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
