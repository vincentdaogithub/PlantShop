package controller.resource;

import controller.redirect.ErrorRedirect;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import security.error.Errors;

@MultipartConfig
public class ImageUploader extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    private final String PART_AVA = "ava";
    private final String PART_PLANT = "plant-img";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        File imgFolder = (File) request.getAttribute("imgFolder");

        if (imgFolder == null) {
            ErrorRedirect.redirect(Errors.FILE_NOT_FOUND, request, response);
            return;
        }
        
        Resources resource = (Resources) request.getAttribute("resourceType");
        Part imgPart;
        
        switch (resource) {
            case AVATAR:
                imgPart = request.getPart(PART_AVA);
                break;
                
            case PLANT:
                imgPart = request.getPart(PART_PLANT);
                break;
                
            default:
                ErrorRedirect.redirect(Errors.SERVER_ERROR, request, response);
                return;
        }

        String imgType = getServletContext().getMimeType(imgPart.getSubmittedFileName());
        StringBuilder builder = new StringBuilder();
        builder.append(imgFolder.getAbsolutePath());
        builder.append("\\img.");
        
        switch (imgType) {
            case "image/jpeg":
                builder.append("jpg");
                break;
                
            case "image/png":
                builder.append("png");
                break;
                
            case "image/gif":
                builder.append("gif");
                break;
                
            default:
                request.setAttribute("avaUpdateFail", true);
                return;
        }
        
        File imgFile = new File(builder.toString());
        
        if (!imgFile.isFile()) {
            imgFile.createNewFile();
        }

        try (
            BufferedInputStream in = new BufferedInputStream(imgPart.getInputStream());
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imgFile));
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
