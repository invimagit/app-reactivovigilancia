ALTER TABLE [SIVICOS].[dbo].[parametros] ALTER COLUMN [consec_friarh] int; 
UPDATE [dbo].[parametros]
   SET [consec_friarh] = 200900900
GO