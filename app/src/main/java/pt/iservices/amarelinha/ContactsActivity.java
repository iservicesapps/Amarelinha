package pt.iservices.amarelinha;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mariocosme on 02/06/15.
 */

public class ContactsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity);
        setUpLayout();
    }

    private void setUpLayout() {
        Typeface chalkboardBold = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard-Bold.ttf");
        Typeface chalkboard = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard.ttf");
        Typeface chalkduster = Typeface.createFromAsset(getAssets(), "fonts/Chalkduster.ttf");
        TextView titleTV = (TextView) findViewById(R.id.titleTV);
        titleTV.setTypeface(chalkboardBold);
        TextView horarioTv = (TextView) findViewById(R.id.horarioTv);
        horarioTv.setTypeface(chalkduster);
        TextView horarioDescTv = (TextView) findViewById(R.id.horarioDescTv);
        horarioDescTv.setTypeface(chalkboard);

        // TODO change button typeface
    }

    public void call(View v) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:923567288"));
        startActivity(intent);
    }

    // TODO adicionar ao main menu info->  Amarelinha Hamburguer's é uma pequena empresa de jovens angolanos que nasceu de um sonho... Fazer os melhores lanches da cidade (Hamburgueres, Bifanas, Pregos, Fahitas, entre outros) e prestar um bom serviço aos seus clientes!\nEm 2012 começavamos esta aventura!\n\nO nosso objectivo é de proporcionar aos Angolanos, um serviço fidedigno e com qualidade, prestar um serviço personalizado e eficiente.\n\nTemos sempre como objectivo, a qualidade da oferta, a eficiência do serviço e a satisfação do cliente, por isso, colocamos todo o nosso amor no nosso producto.\n\n\nOs nossos Produtos são artesanais (produzimos a nossa própria carne e muito em breve os seremos nós a produzir o nosso pão), tudo feito com bastante amor e carinho.\n\n\nAgora que já sabe um pouco a nosso respeito esteja atento às nossas redes sociais, participe nos nossos passatempos com direito a lanches de oferta e sempre que fizer uma compra, peça o seu código para inserir na zona do &quot;cartão de refeição&quot;, quando acumular 10 códigos terá direito a um lanche de oferta à sua escolha.
}