Feature: Flujo de Busqueda

  @flujo-feliz
  Scenario: : Flujo de busqueda
    Given el usuario ingresa a la pagina de PCFactory
    And el usuario realiza la busqueda del producto "Procesador"
    And el usuario selecciona el producto "CPU Celeron G1820"
    Then Se valida que el usuario haya seleccionado el producto "CPU Celeron G1820"

  @flujo-feliz2
  Scenario: : Flujo de busqueda
    Given el usuario ingresa a la pagina de PCFactory
    And el usuario realiza la busqueda del producto "Disco SSD"
    And el usuario selecciona el producto "Cofre SSD M.2 SATA"
    Then Se valida que el usuario haya seleccionado el producto "Cofre SSD M.2 SATA"