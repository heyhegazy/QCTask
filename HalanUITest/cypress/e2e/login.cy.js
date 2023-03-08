describe('login spec', () => {
  
  before(() => {

    cy.visit('http://www.testyou.in/Login.aspx');
  
  })

  it('Login invalid attempt', () => {
      
    cy.get('#ctl00_CPHContainer_txtUserLogin').type("randomemail@halan.com")
    cy.get('#ctl00_CPHContainer_txtPassword').type("randompassword")

    cy.get('#ctl00_CPHContainer_btnLoginn').click()

    cy.get('#ctl00_CPHContainer_lblOutput').contains('Userid or Password did Not Match !!')

  })
  
})