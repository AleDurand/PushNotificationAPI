package project;

import java.text.SimpleDateFormat;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

	@Value("${http.proxyHost}")
	public String PROXY_HOST;

	@Value("${http.proxyPort}")
	public Integer PROXY_PORT;

	@Value("${http.proxyUser}")
	public String PROXY_USER;

	@Value("${http.proxyPassword}")
	public String PROXY_PASSWORD;

	@Value("${http.proxySet:false}")
	public boolean PROXY_SET;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
		messageBundle.setBasename("classpath:messages");
		messageBundle.setDefaultEncoding("UTF-8");
		return messageBundle;
	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		builder.serializationInclusion(Include.NON_NULL);
		builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));
		builder.failOnUnknownProperties(false);
		return builder;
	}
		
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
		p.setIgnoreResourceNotFound(true);
		p.setIgnoreUnresolvablePlaceholders(true);
		return p;
	}

	@Bean
	public RestTemplate restTemplate() {
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		if (PROXY_SET) {
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
				new AuthScope(PROXY_HOST, PROXY_PORT),
				new UsernamePasswordCredentials(PROXY_USER, PROXY_PASSWORD)
			);

			HttpHost myProxy = new HttpHost(PROXY_HOST, PROXY_PORT);

			clientBuilder.setProxy(myProxy)
				.setDefaultCredentialsProvider(credsProvider)
				.disableCookieManagement();
		}

		HttpClient httpClient = clientBuilder.build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient);
		return new RestTemplate(factory);
	}

}
