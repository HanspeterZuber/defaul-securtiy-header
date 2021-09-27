package de.adesso.techtalk.http.header.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                antMatchers("/**").
                permitAll();

        configureCases(http);
    }

    protected void configureCases(HttpSecurity http) throws Exception {
        configureDisableAllHeader(http);

//        configureContentTypeOptions(http);

//        //X-Frame-Options
//        configureXFrameOptions(http);
//
//        configureReferrer(http);//
//
//        //case csp
//        configureContentSecurityPolicy(http);
//
//        configurePermissionPolicy(http);
//
//        //HSTS - mit SSL nur...
//        configureHTTPStrictTransportSecurity(http);
    }

    private void configureDisableAllHeader(HttpSecurity http) throws Exception {
        http.
                headers(headers -> headers.disable());
    }

    private void configureHTTPStrictTransportSecurity(HttpSecurity http) throws Exception {
        http.
                headers(headers -> headers
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .preload(true)
                                .maxAgeInSeconds(31536000)
                        )
                );
    }

    private void configureReferrer(HttpSecurity http) throws Exception {
        http.
                headers(headers -> headers
                        .referrerPolicy(referrer -> referrer
                                .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN)
                        )
                );
    }

    private void configureContentTypeOptions(HttpSecurity http) throws Exception {
        //Content-Type-Options -> noSniff by default
        http.
                headers(headers -> headers
                        .contentTypeOptions(contentTypeOptions -> contentTypeOptions.disable())
                );
    }

    protected void configureXFrameOptions(HttpSecurity http) throws Exception {
        http.
                headers(headers -> headers.
                                frameOptions(frameOptions -> frameOptions.
//                       disable()
                       sameOrigin()
//        deny()
                                )
                );
    }

    protected void configureContentSecurityPolicy(HttpSecurity http) throws Exception {
        http.
                headers(headers -> headers.
                                frameOptions(frameOptions -> frameOptions.
                                        disable()
                                ).
                                contentSecurityPolicy(csp -> csp
                                                .policyDirectives("default-src 'self';")
//                                .policyDirectives("default-src 'self'; script-src 'self' https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js; style-src 'self' https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css")
//                                .policyDirectives("default-src 'self'; script-src 'self' https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js; style-src 'self' https://cdn.jsdelivr.net; font-src 'self' https://cdn.jsdelivr.net;")
                                )
                );
    }

    protected void configurePermissionPolicy(HttpSecurity http) throws Exception {
        http.
                headers(headers -> headers
                        .permissionsPolicy(permissions -> permissions
                                .policy("geolocation=(self)")
                        )
                );
    }


}
