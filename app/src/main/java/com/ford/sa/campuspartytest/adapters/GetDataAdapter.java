package com.ford.sa.campuspartytest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ford.sa.campuspartytest.CarData;
import com.ford.sa.campuspartytest.R;
import com.ford.sa.campuspartytest.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;

/**
 * Created by BGARCI67 on 15/01/2018.
 */

public class GetDataAdapter extends RecyclerView.Adapter<GetDataAdapter.GetDataViewHolder> {


    private Context mContext;
    private List<CarData> mListCarData;
    private LayoutInflater mLayoutInflater;

    private String CATEGORIA = "AreceberAdapter";
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private float scale;
    private int width;
    private int height;

    public GetDataAdapter(Context _ctx, List<CarData> _ListCarData){
        this.mListCarData = _ListCarData;
        this.mContext = _ctx;

        scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale);
        height = (width / 16) * 9;

        this.mLayoutInflater = (LayoutInflater)_ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public GetDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(CATEGORIA, "onCreateViewHolder()");
        View v = mLayoutInflater.inflate(R.layout.item_areceber_card, parent, false);
        AreceberViewHolder mvh = new AreceberViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(GetDataViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GetDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //public RelativeLayout rrAreceber;
        //public TextView txtStatusAreceber, txtDataPagamento, txtValorPago, txtDescricao, txtVencimento, txtValorMensal, txtFormaPagamento;

        public GetDataViewHolder(View itemView) {
            super(itemView);
            //rrAreceber = (RelativeLayout) itemView.findViewById(R.id.rrAreceber);
            //txtStatusAreceber = (TextView)itemView.findViewById(R.id.txtStatusAreceber);
            //txtDataPagamento = (TextView)itemView.findViewById(R.id.txtDataPagamento);
            //txtValorPago = (TextView)itemView.findViewById(R.id.txtValorPago);
            //txtDescricao = (TextView)itemView.findViewById(R.id.txtDescricao);
            //txtVencimento = (TextView)itemView.findViewById(R.id.txtVencimento);
            //txtValorMensal = (TextView)itemView.findViewById(R.id.txtValorMensal);
            //txtFormaPagamento = (TextView)itemView.findViewById(R.id.txtFormaPagamento);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //if(mRecyclerViewOnClickListenerHack != null){
             //   mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            //}
        }
    }

}
