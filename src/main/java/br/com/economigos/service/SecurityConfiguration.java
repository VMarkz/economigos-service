package br.com.economigos.service;

import br.com.economigos.service.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/economigos/sessao/login").permitAll()
                .antMatchers("/economigos/sessao/logout").permitAll()
                .antMatchers("/economigos/sessao/authenticate").permitAll()
                .antMatchers(HttpMethod.GET,"/economigos/usuarios").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/economigos/usuarios").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/usuarios/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/usuarios/email").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/usuarios/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/economigos/usuarios/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/usuarios/{id}/ultimos-meses").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/usuarios/lancamentos").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/economigos/usuarios/amigos").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/usuarios/{idUsuario}/amigos").hasAnyAuthority("USER","ADMIN")                .antMatchers(HttpMethod.POST,"/economigos/usuarios/amigos").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/economigos/usuarios/amigos").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/rendas").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/economigos/rendas").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/rendas/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/rendas/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/economigos/rendas/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/rendas/receber/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/metas").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/economigos/metas").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/metas/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/metas/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PATCH,"/economigos/metas/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PATCH,"/economigos/metas/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PATCH,"/economigos/metas/{id}/desativar-ativar").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PATCH,"/economigos/metas/{id}/finalizar").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PATCH,"/economigos/metas/{id}/finalizar").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/economigos/metas/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/gastos").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/economigos/gastos").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/gastos/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/gastos/pagar/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/gastos/cancelar-pagamento/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,"/economigos/gastos/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/contatos").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/economigos/contatos").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/contatos/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/contas").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/contas/{idConta}/ultimos-meses").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/economigos/contas").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/contas/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/contas/conta").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/contas/{idConta}/ultimas-atividades").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/contas/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/economigos/contas/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/categorias").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/categorias/porcentagem-gastos").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/economigos/categorias").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/categorias/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/categorias/categoria").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/categorias/gastos").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/categorias/receitas").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/categorias/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/economigos/categorias/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/categorias/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/cartoes").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/economigos/cartoes").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/cartoes/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/economigos/cartoes/{idCartao}/ultimas-atividades").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/cartoes/{id}").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.PUT,"/economigos/cartoes/pagar").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/economigos/cartoes/{id}").hasAnyAuthority("USER","ADMIN")


                .antMatchers("/h2-console/**").permitAll()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin()
                .and().csrf().disable().authorizeRequests().antMatchers("/economigos/sessao/authenticate").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
