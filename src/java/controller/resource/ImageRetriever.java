package controller.resource;

import controller.redirect.ErrorRedirect;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import security.error.Errors;

public class ImageRetriever extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        File img = (File) request.getAttribute("imgFile");

        if (img == null) {
            ErrorRedirect.redirect(Errors.FILE_NOT_FOUND, request, response);
            return;
        }

        response.setContentType(getServletContext().getMimeType(img.getName()));

        try (
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(img));
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        ) {
            byte[] reader = new byte[1024];

            while (in.read(reader) != -1) {
                out.write(reader);
            }
        } catch (Exception e) {
            ErrorRedirect.redirect(Errors.FILE_NOT_FOUND, request, response);
        }
    }
}
