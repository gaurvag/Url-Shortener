# URL Shortener

A simple URL shortening service built with Spring Boot and MongoDB, capable of supporting up to 3.5 trillion unique short URLs.

## Demo
*Hosted Here:* https://url-shortner-rouge.vercel.app/

*1) Shorten a Url*
<img width="900" alt="image" src="https://github.com/gaurvag/Url-Shortener/assets/56625397/618ce622-32dd-4234-bc8f-6c8e2891c541">

*2) Redirect to the Original URL*
<img width="900" alt="image" src="https://github.com/gaurvag/Url-Shortener/assets/56625397/61dae585-9d98-4705-883d-caca6d4f6c2e">

*3) Checking Clicks on the Shortened Url*
<img width="900" alt="image" src="https://github.com/gaurvag/Url-Shortener/assets/56625397/be066b75-e1e7-4d63-8e8c-74e393fb376b">

## Getting Started

*Prerequisites:* 
1. Ensure you have Java and MongoDB installed on your system.
2. Clone the Repository: Clone this repository to your local machine.

*Running the Project in Local:*

   - Open the project in your Spring Boot-compatible IDE, such as IntelliJ IDEA.
   - Checkout to develop branch.
   - Build and run the project to start the URL Shortener service.
   - You can now use API calls to interact with the URL Shortener. Below are examples of API calls using cURL, which you can also perform in Postman.

*API Endpoints:*

   - *Shorten a URL:*

     Use this API to shorten a long URL.

     ```shell
     curl --location --request POST 'http://localhost:8018/url/shorten' \
     --header 'Content-Type: application/json' \
     --data '{
         "url": "https://example.com/your/long/url"
     }'
     ```
      <img width="900" alt="image" src="https://github.com/gaurvag/Url-Shortener/assets/56625397/8d92002c-3924-4df3-917b-2b98178bd5c3">

  The response will contain the shortened URL.

   - *Redirect to the Original URL:*

     Use this API to redirect to the original URL using a shortened key.

     ```shell
     curl --location --request GET 'http://localhost:8018/url/redirect/h'
     ```
       <img width="900" alt="image" src="https://github.com/gaurvag/Url-Shortener/assets/56625397/ad637eb9-6a45-4dbd-b5f0-e874882c6f77">
 

   Simply paste the URL in your browser, and it will redirect you to the original URL associated with the provided key.
       <img width="800" alt="image" src="https://github.com/gaurvag/Url-Shortener/assets/56625397/f866957c-06ec-4407-a9a0-92857d3a6bdf">


Enjoy
