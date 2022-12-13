class Drink
  def initialize(beverage: , sugar:, milk: )
    if beverage == :cappuchino && !milk
      raise "Nonsense"
    end

    @beverage = beverage
    @sugar    = sugar
    @milk     = milk
  end
end
