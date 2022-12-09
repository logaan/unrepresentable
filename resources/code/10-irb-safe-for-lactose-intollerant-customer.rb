def safe_for_lactose_intollerant_customers?(drink)
  return drink.milk == false
end

irb(main):015:0>
    safe_for_lactose_intollerant_customers?(
      Cappuchino.new(sugar: false))

Traceback (most recent call last):
5: from /usr/bin/irb:23:in '&lt;main&gt;'
4: from /usr/bin/irb:23:in 'load'
3: from /Library/Ruby/Gems/2.6.0/gems/irb-1.0.0/exe/irb:11:in
   '&lt;top (required)&gt;'
2: from (irb):15
1: from (irb):13:in 'safe_for_lactose_intollerant_customer?'
NoMethodError (undefined method 'milk' for
    #&lt;Cappuchino:0x000000014e8e6620 @sugar=false&gt;)
