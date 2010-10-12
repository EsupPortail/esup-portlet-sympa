from SOAPpy import WSDL

email = 'monemail'
password = 'monpassword'
sympawsdl = 'http://sympa.monuniv/sympa/wsdl'

WSDLFILE = sympawsdl
sympa = WSDL.Proxy(WSDLFILE)
cookie = sympa.login(email, password)
print sympa.authenticateAndRun(email, cookie, 'which')

