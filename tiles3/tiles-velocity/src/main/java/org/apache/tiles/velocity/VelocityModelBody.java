package org.apache.tiles.velocity;

import java.io.IOException;
import java.io.Writer;

import org.apache.tiles.template.body.AbstractModelBody;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.node.ASTBlock;

public class VelocityModelBody extends AbstractModelBody {

    private ASTBlock body;

    private InternalContextAdapter context;

    public VelocityModelBody(InternalContextAdapter context, ASTBlock body, Writer defaultWriter) {
        super(defaultWriter);
        this.context = context;
        this.body = body;
    }

    @Override
    public void evaluate(Writer writer) throws IOException {
        body.render(context, writer);
    }

}
