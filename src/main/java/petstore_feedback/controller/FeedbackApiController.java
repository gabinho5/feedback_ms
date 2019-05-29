package petstore_feedback.controller;
import org.springframework.beans.factory.annotation.Autowired;
import petstore_feedback.services.*;
import petstore_feedback.model.*;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import petstore_feedback.model.ErrorResponse;
import petstore_feedback.model.Feedback;
import petstore_feedback.model.FeedbackInput;
import petstore_feedback.model.SuccessfulResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.cloudant.client.api.Database;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloudant.client.api.model.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-28T13:26:34.286Z")

@Controller
public class FeedbackApiController implements FeedbackApi {
	@Autowired
	 private CreateFeedbackI createFeedbackI;
	@Autowired
     private GetFeedbackI getFeedbackI;
     @Autowired
          private Database feedbackDB;


    private static final Logger log = LoggerFactory.getLogger(FeedbackApiController.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final HttpServletRequest request = new HttpServletRequest(){
    
        @Override
        public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
                throws IllegalStateException {
            return null;
        }
    
        @Override
        public AsyncContext startAsync() throws IllegalStateException {
            return null;
        }
    
        @Override
        public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
            
        }
    
        @Override
        public void setAttribute(String name, Object o) {
            
        }
    
        @Override
        public void removeAttribute(String name) {
            
        }
    
        @Override
        public boolean isSecure() {
            return false;
        }
    
        @Override
        public boolean isAsyncSupported() {
            return false;
        }
    
        @Override
        public boolean isAsyncStarted() {
            return false;
        }
    
        @Override
        public ServletContext getServletContext() {
            return null;
        }
    
        @Override
        public int getServerPort() {
            return 0;
        }
    
        @Override
        public String getServerName() {
            return null;
        }
    
        @Override
        public String getScheme() {
            return null;
        }
    
        @Override
        public RequestDispatcher getRequestDispatcher(String path) {
            return null;
        }
    
        @Override
        public int getRemotePort() {
            return 0;
        }
    
        @Override
        public String getRemoteHost() {
            return null;
        }
    
        @Override
        public String getRemoteAddr() {
            return null;
        }
    
        @Override
        public String getRealPath(String path) {
            return null;
        }
    
        @Override
        public BufferedReader getReader() throws IOException {
            return null;
        }
    
        @Override
        public String getProtocol() {
            return null;
        }
    
        @Override
        public String[] getParameterValues(String name) {
            return null;
        }
    
        @Override
        public Enumeration<String> getParameterNames() {
            return null;
        }
    
        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }
    
        @Override
        public String getParameter(String name) {
            return null;
        }
    
        @Override
        public Enumeration<Locale> getLocales() {
            return null;
        }
    
        @Override
        public Locale getLocale() {
            return null;
        }
    
        @Override
        public int getLocalPort() {
            return 0;
        }
    
        @Override
        public String getLocalName() {
            return null;
        }
    
        @Override
        public String getLocalAddr() {
            return null;
        }
    
        @Override
        public ServletInputStream getInputStream() throws IOException {
            return null;
        }
    
        @Override
        public DispatcherType getDispatcherType() {
            return null;
        }
    
        @Override
        public String getContentType() {
            return null;
        }
    
        @Override
        public long getContentLengthLong() {
            return 0;
        }
    
        @Override
        public int getContentLength() {
            return 0;
        }
    
        @Override
        public String getCharacterEncoding() {
            return null;
        }
    
        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }
    
        @Override
        public Object getAttribute(String name) {
            return null;
        }
    
        @Override
        public AsyncContext getAsyncContext() {
            return null;
        }
    
        @Override
        public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass)
                throws IOException, ServletException {
            return null;
        }
    
        @Override
        public void logout() throws ServletException {
            
        }
    
        @Override
        public void login(String username, String password) throws ServletException {
            
        }
    
        @Override
        public boolean isUserInRole(String role) {
            return false;
        }
    
        @Override
        public boolean isRequestedSessionIdValid() {
            return false;
        }
    
        @Override
        public boolean isRequestedSessionIdFromUrl() {
            return false;
        }
    
        @Override
        public boolean isRequestedSessionIdFromURL() {
            return false;
        }
    
        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return false;
        }
    
        @Override
        public Principal getUserPrincipal() {
            return null;
        }
    
        @Override
        public HttpSession getSession(boolean create) {
            return null;
        }
    
        @Override
        public HttpSession getSession() {
            return null;
        }
    
        @Override
        public String getServletPath() {
            return null;
        }
    
        @Override
        public String getRequestedSessionId() {
            return null;
        }
    
        @Override
        public StringBuffer getRequestURL() {
            return null;
        }
    
        @Override
        public String getRequestURI() {
            return null;
        }
    
        @Override
        public String getRemoteUser() {
            return null;
        }
    
        @Override
        public String getQueryString() {
            return null;
        }
    
        @Override
        public String getPathTranslated() {
            return null;
        }
    
        @Override
        public String getPathInfo() {
            return null;
        }
    
        @Override
        public Collection<Part> getParts() throws IOException, ServletException {
            return null;
        }
    
        @Override
        public Part getPart(String name) throws IOException, ServletException {
            return null;
        }
    
        @Override
        public String getMethod() {
            return null;
        }
    
        @Override
        public int getIntHeader(String name) {
            return 0;
        }
    
        @Override
        public Enumeration<String> getHeaders(String name) {
            return null;
        }
    
        @Override
        public Enumeration<String> getHeaderNames() {
            return null;
        }
    
        @Override
        public String getHeader(String name) {
            return null;
        }
    
        @Override
        public long getDateHeader(String name) {
            return 0;
        }
    
        @Override
        public Cookie[] getCookies() {
            return null;
        }
    
        @Override
        public String getContextPath() {
            return null;
        }
    
        @Override
        public String getAuthType() {
            return null;
        }
    
        @Override
        public String changeSessionId() {
            return null;
        }
    
        @Override
        public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
            return false;
        }
    };

    public ResponseEntity<List<Feedback>> feedbackGet() {
        List<Feedback> allDocs = new ArrayList<Feedback>();
        try {
          allDocs = feedbackDB.getAllDocsRequestBuilder()
                              .includeDocs(true)
                              .build()
                              .getResponse()
                              .getDocsAs(Feedback.class);
        } catch (Exception e) {
            log.error("error occurred: {}", e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Feedback>>(allDocs, HttpStatus.OK);
      }

    public ResponseEntity<SuccessfulResponse> createFeedback(@ApiParam(value = ""  )  @Valid @RequestBody FeedbackInput body) {

	request.setAttribute("operationId","createFeedback");
	CreateFeedbackResponseWrapper res = createFeedbackI.execute(new CreateFeedbackP(body));
return new ResponseEntity<SuccessfulResponse>(res.getResponse(),res.getHeaders(),HttpStatus.OK);
    }

    public ResponseEntity<List<Feedback>> getFeedback() {

	request.setAttribute("operationId","getFeedback");
	GetFeedbackResponseWrapper res = getFeedbackI.execute(new GetFeedbackP());
return new ResponseEntity<List<Feedback>>(res.getResponse(),res.getHeaders(),HttpStatus.OK);
    }

}
