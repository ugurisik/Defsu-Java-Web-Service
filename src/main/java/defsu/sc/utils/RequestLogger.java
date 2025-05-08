package defsu.sc.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RequestLogger extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // İstek zamanını al
        LocalDateTime requestTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // İstek bilgilerini al
        String ipAddress = request.getRemoteAddr();
        String method = request.getMethod();
        String endpoint = request.getRequestURI();
        String queryString = request.getQueryString();
        String fullUrl = endpoint + (queryString != null ? "?" + queryString : "");

        // Log mesajını oluştur
        String logMessage = String.format(
                "REQUEST: Time=[%s] | IP=[%s] | Method=[%s] | Endpoint=[%s]",
                requestTime.format(formatter), ipAddress, method, fullUrl);

        // Log'u yazdır
        logger.info(logMessage);

        // Filtreleme zincirini devam ettir
        filterChain.doFilter(request, response);
    }
}
