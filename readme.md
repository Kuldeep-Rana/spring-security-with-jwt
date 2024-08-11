### This is a sample application to showcase JWT authentication in Spring. 

## what is JWT?

JSON Web Token (JWT) is an open standard (RFC 7519) used for securely transmitting information between parties as a JSON object. It's commonly used for authentication and authorization purposes in web applications.
### Key Components of JWT

**Header-**  Specifies the type of token (JWT) and the signing algorithm (e.g., HS256).
example: ``{
"alg": "HS256",
"typ": "JWT"
}``

**Payload-** Contains the claims or the data being transmitted. Claims can be standard (e.g., iss for issuer, exp for expiration) or custom.
``{
"sub": "1234567890",
"name": "John Doe",
"iat": 1516239022
}
``

**Signature-** Created by encoding the header and payload, and then signing them with a secret key or private key. This ensures the token's integrity and authenticity.
``HMACSHA256(
base64UrlEncode(header) + "." +
base64UrlEncode(payload),
secret
)
``
### I have implemented the following in this project. 

* Sample rest api
* Authentication and authorization using JWT
* Role base access for specific endpoint. For example user with ADMIN role can access all the endpoints. User with manager role can access all the endpoints except allowed only ADMIN.    
* Any other endpoint which is not restricted explicitly can be accessed by all the users.
* To enable role base access we have to add **hasRole(String role)** or **hasAnyRole(String... roles)**.
* The **sessionCreationPolicy(SessionCreationPolicy.STATELESS)** is helpfully since we don;t have to maange the session as all the validation for users are being done using JWT token.

