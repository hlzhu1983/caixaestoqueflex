package utilidades
{
	import mx.validators.ValidationResult;
	import mx.validators.Validator;

	public class ValidatorGenerico extends Validator
	{
		
		public static const INTEIRO:String = "\[0-9]+";
		
		public static const VALOR:String = "\[0-9]+,\[0-9]+|\[0-9]+";
		
		public static const ID:String = "\\[A-Za-z]"; 
		
		
		private var results:Array;
		
		public var expRegular:String;
		
		public var msgErro:String;
		
		
		public function ValidatorGenerico()
		{
			super();
			this.requiredFieldError = "Campo Obrigatório!"
		}
		
		protected override function doValidation(value:Object):Array{
			this.results = [];
			this.results = super.doValidation(value);
			if(value!=null){
				var reg:RegExp = new RegExp(expRegular);
				if(value.search(reg) ==-1){						
					results.push(new ValidationResult(true,null,"Erro",msgErro));		
				}
			}			
			return results;
		}
		
	}
}